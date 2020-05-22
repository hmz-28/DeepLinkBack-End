package sm.deeplink.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.deeplink.entity.User;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User save(User user);

    @Transactional
    @Query("select u from User u where u.userId = :userid")
    User getUserById(@Param("userid") Long userid);

}