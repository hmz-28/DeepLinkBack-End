package sm.deeplink.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.service.LinkService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/deeplinks")
public class LinkController {

    @Autowired
    private LinkService linkService;


    @GetMapping("/users/{userid}/links")
    public List<DeepLink> getLinksByUserId(@PathVariable Long userid) {

        return linkService.findLinkByUserId(userid);
    }

    @PostMapping("/users/{userid}/link")
    public DeepLink addLink(@PathVariable Long userid,
                            @Valid @RequestBody DeepLink link) {

        return linkService.saveLinkByUserId(userid, link);

    }

    @PutMapping("/users/{userid}/links/{linkid}")
    public DeepLink updateLink(@PathVariable Long userid,
                               @PathVariable Long linkid,
                               @Valid @RequestBody DeepLink linkUpdated) {

        return linkService.updateLinkByUserId(userid, linkid, linkUpdated);


    }

    @DeleteMapping("/users/{userid}/link/{linkid}")
    public ResponseEntity<?> deleteLink(@PathVariable Long userid,
                                        @PathVariable Long linkid) {
        return linkService.deleteLink(userid, linkid);
    }
}
