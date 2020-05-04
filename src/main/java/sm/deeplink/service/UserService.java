package sm.deeplink.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sm.deeplink.entity.User;

import java.util.List;
@Service
public interface UserService {
    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    public void deleteUser(User user);

    User findUserAccount(String userName);
}
