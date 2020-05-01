package sm.deeplink.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sm.deeplink.entity.User;
import sm.deeplink.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/deeplink")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/userList")
    public List<User> getEmployees() {
        return userService.findAll();

    }
}
