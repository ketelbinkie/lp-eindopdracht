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

import static nl.hanze.application.util.Session.*;

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
        return new User();
    }

    @RequestMapping(value = "/users/finduser")
    public User findUser(
            @RequestParam(value = "user") String name) {
        return userService.findUserByUserName(name);

    }

    @RequestMapping(value = "/users/roles")
    public List<Role> findUser() {
        return userService.findAllRoles();

    }

    @PostMapping(value = "/users/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(
            @Valid @RequestBody User user) {

        try {
            if (user != null) {
                userService.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Gebruiker is succesvol toegevoegd!");
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Gebruker kon niet wordern toegevoegd!");
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
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



