package sm.deeplink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sm.deeplink.dao.LinkDao;
import sm.deeplink.dao.UserDao;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.exception.GlobalExceptionHandler;

import java.util.List;
@Service(value = "linkServiceImpl")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;


    @Autowired
    private UserDao userDao;

    @Override
    public List<DeepLink> findLinkByUserId(Long userid) {

        if(!linkDao.existsById(userid)) {
            throw new GlobalExceptionHandler("User not found!");
        }
        return linkDao.findLinkByUserId(userid);
    }

    @Override
    public DeepLink saveLinkByUserId(Long userid, DeepLink link) {
        return userDao.findById(userid)
                .map(user -> {
                    link.setUser(user);
                    return linkDao.save(link);
                }).orElseThrow(() -> new GlobalExceptionHandler("User not found!"));
    }

    @Override
    public DeepLink updateLinkByUserId(Long userid, Long linkid, DeepLink linkUpdated) {
        if(!userDao.existsById(userid)) {
            throw new GlobalExceptionHandler("User not found!");
        }

        return linkDao.findById(linkid)
                .map(link -> {
                    link.setLinkname(linkUpdated.getLinkname());
                    link.setLinkvalue(linkUpdated.getLinkvalue());
                    return linkDao.save(link);
                }).orElseThrow(() -> new GlobalExceptionHandler("Link not found!"));
    }

    @Override
    public String deleteLink(Long userid, Long linkid) {
        if(!userDao.existsById(userid)) {
            throw new GlobalExceptionHandler("User not found!");
        }

        return linkDao.findById(linkid)
                .map(link -> {
                    linkDao.delete(link);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new GlobalExceptionHandler("Link not found!"));
    }

}
