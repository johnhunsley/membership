<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Membership Management</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link href="/static/css/app.css" rel="stylesheet">
    <link href="/static/css/bootstrap.css" rel="stylesheet">
</head>
<div class="generic-container">
    <div class="panel panel-default">
        <body>
            <form method="POST">
                <div class="panel-heading"><span class="lead">Membership Management Login</span></div>
                <div class="formcontainer">
                    <table>
                        <tr>
                            <td class="control-lable" align="right">Username</td>
                            <td><input class="form-control input-sm" type="text" name="username"></td>
                        </tr>
                        <tr>
                            <td class="control-lable" align="right">Password</td>
                            <td><input class="form-control input-sm" type="password" name="password"></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right">
                                <input class="btn btn-success custom-width" type="submit" value="Login">
                                <input class="btn btn-danger custom-width" type="reset" value="Reset">
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </body>
    </div>
</div>
</html>