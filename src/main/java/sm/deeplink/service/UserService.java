package sm.deeplink.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sm.deeplink.entity.User;

import java.util.List;

public interface UserService {
     User save(User user);

    List<User> findAll();

    void delete(int id);

    User findOne(String username);

    User findById(int id);

    User update(User userDto);

    User getUserById(Long userid);
}
