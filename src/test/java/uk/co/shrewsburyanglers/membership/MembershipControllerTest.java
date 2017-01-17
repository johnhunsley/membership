package uk.co.shrewsburyanglers.membership;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.shrewsburyanglers.membership.model.MemberEntity;
import uk.co.shrewsburyanglers.membership.model.MemberStatus;
import uk.co.shrewsburyanglers.membership.controller.MembershipController;
import uk.co.shrewsburyanglers.membership.service.MembershipService;

import java.sql.Date;

import static junit.framework.TestCase.fail;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author John Hunsley
 *         (J00074Hunsle)
 *         john.hunsley@avox.info
 *         Date : 03/10/2016
 *         Time : 15:01
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MembershipController.class)
public class MembershipControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MembershipService membershipService;

    @Test
    public void getMemberTest() {

        try {
            given(membershipService.getMemberEntity(anyInt())).willReturn(
                    new MemberEntity("john", "hunsley", "12345 Main Road", "SY3 7ED",
                            "07538178963", Date.valueOf("1975-09-23"), "jphunsley@gmail.com",
                            MemberStatus.ACTIVE, "http://myurl.com"));
//            mvc.perform(get("/12345").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());


        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
