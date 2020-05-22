package sm.deeplink.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import sm.deeplink.entity.DeepLink;

import java.util.List;

public interface LinkService {
    List<DeepLink> findLinkByUserId(Long userid);

    DeepLink saveLinkByUserId(Long userid, DeepLink link);

    DeepLink updateLinkByUserId(Long userid, Long linkid, DeepLink linkUpdated);

    ResponseEntity<?> deleteLink(Long userid, Long linkid);

    Page<DeepLink> findAllLinksPage(Long userid ,Pageable pageable);
}
