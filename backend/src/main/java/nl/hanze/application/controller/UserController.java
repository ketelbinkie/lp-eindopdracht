package nl.hanze.application.controller;

import nl.hanze.application.domain.Role;
import nl.hanze.application.domain.User;
import nl.hanze.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static nl.hanze.application.session.Session.*;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/users/loggedin")
    public User loggedIn(
            @RequestParam(value = "sessionid") String sessionId) {
        return getLoggedInUser(sessionId);
    }


    @RequestMapping(value = "/users/logout")
    public boolean logOut(
            @RequestParam(value = "sessionid") String sessionId) {
        logoutUser(sessionId);
        return getLoggedInUser(sessionId) == null;
    }

    @RequestMapping(value = "/users/checkcreds")
    public User cechCreds(
            @RequestParam(value = "sessionid") String sessionId,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        User user = userService.findUserBycredential(username, password);
        setLoggedInUser(sessionId, user);

        if (user != null) {
            user.setPassword("***********");
                return user;
        }
        return new User() ;
    }

    @RequestMapping(value = "/users/finduser")
    public User findUser(
            @RequestParam(value = "user") String name) {
        return userService.findUserByUserName(name);

    }

    @RequestMapping(value = "/users/roles")
    public List<Role> findUser(){
        return userService.findAllRoles();

    }

    @PostMapping(value = "/users/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> add(
            @Valid @RequestBody User user) {

    System.out.println("test");
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> del(@PathVariable("id") int id) {

        try {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    //    @RequestMapping(value = "/login")
//    public boolean login(
//            @RequestParam(value = "username") String username,
//            @RequestParam(value = "password") String password) {
//        return userService.checkUserNamePasswordIsCorrect(username, password);
//    }


}



