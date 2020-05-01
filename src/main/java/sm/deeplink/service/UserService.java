package sm.deeplink.service;

import org.springframework.security.core.userdetails.UserDetails;
import sm.deeplink.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User loadUserByUsername(String userName);
}
