package sm.deeplink.dao;

import sm.deeplink.entity.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    void insertUser(User user);

    void updateUser(User user);

    public void deleteUser(User user);

    User findUserAccount(String userName);
}
