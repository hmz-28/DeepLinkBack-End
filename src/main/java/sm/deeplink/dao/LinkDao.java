package sm.deeplink.dao;

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
    @Query("select l from DeepLink l inner join User u on u.userId =l.user.userId and l.user.userId = :userid")
    List<DeepLink> findLinkByUserId(Long userid);
    DeepLink save(DeepLink link);
    void delete(DeepLink link);
}
