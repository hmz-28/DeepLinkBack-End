package sm.deeplink.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.service.LinkService;
import sm.deeplink.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deeplinks")
public class LinkController {

    @Autowired
    private LinkService linkService;


    @GetMapping("/users/{userid}/links")
    public List<DeepLink> getContactByStudentId(@PathVariable Long userid) {


        return linkService.findLinkByUserId(userid);
    }

    @PostMapping("/users/{userid}/link")
    public DeepLink addAssignment(@PathVariable Long userid,
                                    @Valid @RequestBody DeepLink link) {

        return linkService.saveLinkByUserId(userid,link);

    }

    @PutMapping("/users/{userid}/links/{linkid}")
    public DeepLink updateAssignment(@PathVariable Long userid,
                                       @PathVariable Long linkid,
                                       @Valid @RequestBody DeepLink linkUpdated) {

        return linkService.updateLinkByUserId(userid,linkid,linkUpdated);


    }

    @DeleteMapping("/users/{userid}/links/{linkid}")
    public String deleteLink(@PathVariable Long userid,
                                   @PathVariable Long linkid) {
        return linkService.deleteLink(userid, linkid);
    }
}
