package sm.deeplink.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sm.deeplink.dao.UserDao;
import sm.deeplink.entity.User;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements  UserService{

    @Resource
    UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userDao.findUserAccount(userName);

        if (user == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + user);

        return user;
    }
}
