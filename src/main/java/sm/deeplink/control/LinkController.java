package sm.deeplink.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.service.LinkService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/deeplinks")
public class LinkController {

    @Autowired
    private LinkService linkService;

//    @GetMapping("/links")
//    public Page<DeepLink> getAllLinks(@PathVariable Long userid, Pageable pageable) {
//        return linkService.findAllLinksPage(userid, pageable);
//    }

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

    @DeleteMapping("/users/{userid}/links/{linkid}")
    public String deleteLink(@PathVariable Long userid,
                             @PathVariable Long linkid) {
        return linkService.deleteLink(userid, linkid);
    }
}
