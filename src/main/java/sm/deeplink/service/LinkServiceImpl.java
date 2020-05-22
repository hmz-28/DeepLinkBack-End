package sm.deeplink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sm.deeplink.dao.LinkDao;
import sm.deeplink.dao.UserDao;
import sm.deeplink.entity.DeepLink;
import sm.deeplink.exception.GlobalExceptionHandler;
import sm.deeplink.utils.EncryptedDeepLinkUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "linkServiceImpl")
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;


    @Autowired
    private UserDao userDao;

    @Autowired
    private EncryptedDeepLinkUtils aesryptEncoder;

    @Override
    public List<DeepLink> findLinkByUserId(Long userid) {

        if (!userDao.existsById(userid)) {
            throw new GlobalExceptionHandler("User not found!");
        }
        List<DeepLink> userlist = linkDao.findLinkByUserId(userid);
        ArrayList<DeepLink> newUserList = new ArrayList<DeepLink>();

        userlist.forEach(link -> {

            DeepLink newLink = new DeepLink();
            newLink.setId(link.getId());
            newLink.setLinkname(link.getLinkname());
            newLink.setLinkvalue(aesryptEncoder.decrypteLink(link.getLinkvalue()));
            newLink.setCustomer(link.getCustomer());
            newLink.setDescription(link.getDescription());
            newLink.setEditedby(link.getEditedby());
          /*  SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dt.parse(dt.format(link.getModificationdate()));

                newLink.setModificationdate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            newLink.setModificationdate(link.getModificationdate());
            newLink.setProfile(link.getProfile());
            newLink.setStatus(link.getStatus());
            newLink.setEnvironment(link.getEnvironment());
            newLink.setUser(link.getUser());
            newLink.setCryptedlinkvalue("http://www.smartech-tn.com/launch?" +link.getLinkvalue());
            newUserList.add(newLink);
        });

        return newUserList;
    }

    @Override
    public DeepLink saveLinkByUserId(Long userid, DeepLink link) {
        return userDao.findById(userid)
                .map(user -> {
                    link.setUser(user);
                    DeepLink newLink = new DeepLink();
                   // System.out.println(link);
                    newLink.setLinkname(link.getLinkname());
                    newLink.setLinkvalue(aesryptEncoder.encryteLink(link.getLinkvalue()));
                    newLink.setCustomer(link.getCustomer());
                    newLink.setDescription(link.getDescription());
                    newLink.setEditedby(link.getEditedby());
                    newLink.setModificationdate(link.getModificationdate());
                    newLink.setProfile(link.getProfile());
                    newLink.setStatus(link.getStatus());
                    newLink.setEnvironment(link.getEnvironment());
                    newLink.setUser(link.getUser());

                    linkDao.save(newLink);
                    return newLink;
                }).orElseThrow(() -> new GlobalExceptionHandler("User not found!"));
    }

    @Override
    public DeepLink updateLinkByUserId(Long userid, Long linkid, DeepLink linkUpdated) {
        if (!userDao.existsById(userid)) {
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
    public ResponseEntity<?> deleteLink(Long userid, Long linkid) {
        if (!userDao.existsById(userid)) {
            throw new GlobalExceptionHandler("User not found!");
        }

        return linkDao.findById(linkid)
                .map(link -> {
                    linkDao.delete(link);
                    return ResponseEntity.ok().build();//"Deleted Successfully!";
                }).orElseThrow(() -> new GlobalExceptionHandler("Link not found!"));
    }

    @Override
    public Page<DeepLink> findAllLinksPage(Long userid, Pageable pageable) {
        return linkDao.findLByUserId(userid, pageable);
    }

}
