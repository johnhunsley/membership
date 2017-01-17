'use strict';

angular.module('myApp', ['angularModalService','ngMaterial', 'angularUtils.directives.dirPagination']).controller('MembershipController', ['$scope', '$http', '$q', 'ModalService', function($scope, $http, $q, ModalService) {
    var self = this;
    self.member={id:null,firstName:'',lastName:'',email:'',payments:[]};
    self.payment={id:null,newPurchaseDate:'',newStartDate:'',newExpireDate:'',newAmount:'',newPOS:'',newTransID:''}
    self.members=[];
    self.queryvalue = '';

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.showAddMember = showAddMember;
    self.back = back;
    self.showAddPaymentDialog = showAddPaymentDialog;

   var REST_SERVICE_URI = 'http://localhost:8080/membership/';
    //var REST_SERVICE_URI='http://WordPressLB-1332502294.eu-west-1.elb.amazonaws.com:8080/membership/';
    self.totalMembers = 0;

    //pageAllUsers(1);
    fetchAllUsers();
    $scope.hideeditmember = true;

    function showAddMember() {
        $scope.hideeditmember = false;
        $scope.hidelistmembers = true;
        $("#addDOB").datepicker({dateFormat: "yy-mm-dd"});
    }

    function back() {
        self.member={id:null,firstName:'',lastName:'',email:'',payments:[]};
        $scope.hideeditmember = true;
        $scope.hidelistmembers = false;
    }

    $scope.pageChanged = function(newPage) {
        pageAllUsers(newPage);
    };



    function showAddPaymentDialog() {
        var form, dialog, allFields;

        $("#addPurchaseDate").datepicker({dateFormat: "yy-mm-dd"});
        $("#addStartDate").datepicker({dateFormat: "yy-mm-dd"});
        $("#addExpireDate").datepicker({dateFormat: "yy-mm-dd"});

        dialog = $("#payment-modal").dialog({
            autoOpen: false,
            height: 420,
            width: 800,
            modal: true,
            buttons: {
                "Save Payment": function() {
                    $scope.$apply(addPayment(self.member));
                    dialog.dialog("close");
                },
                Cancel: function() {
                    dialog.dialog("close");
                }
            },
            close: function() {
                form[0].reset();
                //allFields.removeClass( "ui-state-error" );
            }
        });

        form = dialog.find("form").on("submit", function(event) {
            event.preventDefault();
            addPayment(self.member);
            dialog.dialog("close");
        });

        dialog.dialog("open");
    }


    function fetchAllUsers(){
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                self.members = response.data;
            },
            function(errResponse){
                console.error('Error while fetching members');
            }
        );
    }

    function pageAllUsers(pageNumber) {
        $http.get(REST_SERVICE_URI+'page/10/'+pageNumber)
            .then(
            function (response) {
                self.members = response.data.content;
                self.totalMembers = response.data.totalElement;
            },
            function(errResponse){
                console.error('Error while fetching members');
            }
        );
    }

    function createMember(member){
        $http.post(REST_SERVICE_URI, member)
            .then(
            function (response) {
                //no response
                //pageAllUsers(1);
                fetchAllUsers();
                back();
            },
            function(errResponse){
                console.error('Error while creating member');
            }
        );
    }

    function addPayment(member) {
        member.payments.push(self.payment);
        console.log('Payments length - '+member.payments.length);
    }

    function updateMember(member, id){
        $http.put(REST_SERVICE_URI+id, member)
            .then(
            function (response) {
               //no response
               // pageAllUsers(1);
                fetchAllUsers();
                back();
            },
            function(errResponse){
                console.error('Error while updating member');
            }
        );
    }

    function deleteUser(id){
        //deactive rather than delete
    }

    function submit() {
        if(self.member.id===null){
            console.log('Saving New Member', self.member);
            createMember(self.member);
        }else{
            updateMember(self.member, self.member.id);
            console.log('Member updated with id ', self.member.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.members.length; i++){
            if(self.members[i].id === id) {
                self.member = angular.copy(self.members[i]);
                $scope.hideeditmember = false;
                $scope.hidelistmembers = true;
                $("#addDOB").datepicker({dateFormat: "yy-mm-dd"});
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.member.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.member={id:null,firstName:'',lastName:'',email:''};
        $scope.hideeditmember = false;
        $scope.hidelistmembers = true;
        $scope.myForm.$setPristine(); //reset Form
    }

}]);