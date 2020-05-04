package sm.deeplink.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sm.deeplink.entity.User;
import sm.deeplink.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/deeplink")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;


    @RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return
                user.getUserName().equals("username") && user.getPassword().equals("password");
    }

    @RequestMapping("/userinfo")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> insertUser(@RequestBody User user){
        System.out.println(user.getUserName());
        User userExists = userService.findUserAccount(user.getUserName());
    if (userExists !=null){
    return new ResponseEntity<String>("Fail -> Username is already taken!",
            HttpStatus.BAD_REQUEST);
    }else{
        User newuser = new User( user.getUsername(),encoder.encode(user.getPassword()));
        userService.insertUser(newuser);
        return ResponseEntity.ok().body("User registered successfully!");
    }
    }

}
