package nl.hanze.application.controller;

import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.User;
import nl.hanze.application.service.UserService;
import nl.hanze.application.session.Session.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static nl.hanze.application.session.Session.getLoggedInUser;
import static nl.hanze.application.session.Session.logoutUser;
import static nl.hanze.application.session.Session.setLoggedInUser;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/loggedin")
    public User loggedIn(
            @RequestParam(value = "sessionid") String sessionId) {
        return getLoggedInUser(sessionId);
    }


    @RequestMapping(value = "/logout")
    public boolean logOut(
            @RequestParam(value = "sessionid") String sessionId) {
        logoutUser(sessionId);
        return getLoggedInUser(sessionId) == null;
    }

    @RequestMapping(value = "/checkcreds")
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
        return null;
    }


    @RequestMapping(value = "/login")
    public boolean login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {
        return userService.checkUserNamePasswordIsCorrect(username, password);
    }

    @RequestMapping(value = "/finduser")
    public User findUser(
            @RequestParam(value = "user") String name) {
        return userService.findUserByUserName(name);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> add(
            @Valid @RequestBody User user) {
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

}



