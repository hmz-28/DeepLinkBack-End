package sm.deeplink.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sm.deeplink.entity.User;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User save(User user);
/*    public List<User> findAll();
    public Page<User> findAll(Pageable p);

    void delete(User e);
    void delete(Integer userid);
    boolean exists( Integer userid);*/
    @Transactional
    @Query("select u from User u where u.userId = :userid")
    User getUserById(@Param("userid") Long userid);
    
}