package sm.deeplink.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sm.deeplink.entity.User;
import sm.deeplink.mapper.UserRowMapper;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements  UserDao{

    NamedParameterJdbcTemplate template;
    public UserDaoImpl(NamedParameterJdbcTemplate template) {
        this.template=template;
    }


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return template.query("select * from user", new UserRowMapper());
    }

    @Override
    public void insertUser(User user) {
        final String sql = "insert into user (userid, username ) values(:userid,:username)";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userid", user.getUserId())
                .addValue("username", user.getUserName());

        template.update(sql,param, holder);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void executeUpdateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User findUserAccount(String userName) {
        try {
            final String sql = "Select e from " + User.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, User.class);
            query.setParameter("userName", userName);

            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
