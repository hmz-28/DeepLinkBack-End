package sm.deeplink.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sm.deeplink.entity.User;
import sm.deeplink.model.ApiResponse;
import sm.deeplink.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/deeplinks")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    /*@PostMapping
    public ApiResponse<User> saveUser(@RequestBody User user){
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }*/
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ApiResponse<List<User>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.",userService.findAll());
    }

    @GetMapping("/user/{id}")
    public ApiResponse<User> getOne(@PathVariable Long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",userService.getUserById(id));
    }

    @PutMapping("/user/{id}")
    public ApiResponse<User> update(@RequestBody User user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",userService.update(user));
    }

    @DeleteMapping("/user/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
        userService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }



}
