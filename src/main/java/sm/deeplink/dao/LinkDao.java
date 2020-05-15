package sm.deeplink.dao;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.entity.User;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface LinkDao extends JpaRepository<DeepLink, Long> {

    @Transactional
    @Query("select l from DeepLink l inner join l.user u where u.userId = :userid")
    List<DeepLink> findLinkByUserId(Long userid);

    DeepLink save(DeepLink link);

    void delete(DeepLink link);

    @Query("select l from DeepLink l inner join l.user u where u.userId = :userid")
    Page<DeepLink> findLByUserId(Long userid, Pageable pageable);
}
