package uk.co.shrewsburyanglers.membership.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MembershipException;
import uk.co.shrewsburyanglers.membership.service.MembershipService;

import java.util.List;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 03/10/2016
 *         Time : 14:36
 */
@RestController
@RequestMapping(value = "membership")
public class MembershipController {
    Logger logger = Logger.getLogger(MembershipController.class);

    @Autowired
    private MembershipService membershipService;

    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MemberEntity>> listMembers() {
        return new ResponseEntity<>(membershipService.getAllMemberEntities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public ResponseEntity<Page> pageMembers(@PathVariable(name = "pageSize") final int pageSize, @PathVariable(name = "pageNumber") final int pageNumber) {
        Page page = membershipService.pageAllMembers(pageNumber, pageSize);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    /**
     *
     * @param memberEntity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MemberEntity> createMember(@RequestBody MemberEntity memberEntity) {

        try {
            membershipService.createMemberWithPayment(memberEntity);
        } catch (MembershipException e) {
            logger.error(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public ResponseEntity<MemberEntity> getMember(@PathVariable(name = "memberId") final int memberId) {
        MemberEntity member = membershipService.getMemberEntity(memberId);

        if(member == null) return new ResponseEntity<MemberEntity>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     *
     * @param memberId
     * @param memberEntity
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberEntity> updateMember(@PathVariable(name = "memberId") final int memberId, @RequestBody MemberEntity memberEntity) {
        if(membershipService.getMemberEntity(memberId) == null) {
            logger.warn("Attempt to update Member with id - "+memberId+" No MemberEntity found with this ID");
            return new ResponseEntity<MemberEntity>(HttpStatus.NOT_FOUND);
        }

        membershipService.updateMember(memberEntity);
        return new ResponseEntity<MemberEntity>(HttpStatus.OK);
    }

    /**
     *
     * @param queryValue
     * @return
     */
    @RequestMapping(value = "/search/{queryvalue}", method = RequestMethod.GET)
    public ResponseEntity<List<MemberEntity>> searchMembers(@PathVariable(name = "queryvalue") final String queryValue) {
        if(queryValue == null || queryValue.length() < 1) return listMembers();

        return new ResponseEntity<>(membershipService.pageSearchMembers(queryValue), HttpStatus.OK);
    }

}
