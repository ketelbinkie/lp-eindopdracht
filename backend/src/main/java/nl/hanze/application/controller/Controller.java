package nl.hanze.application.controller;

import nl.hanze.application.controller.utils.Sum;
import nl.hanze.application.entities.User;
import nl.hanze.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller( UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/hello")
    public User hello(
            @RequestParam(value = "name") String name) {
        List<User> userList = userService.findAll();
        String password;
        for (User user:userList) {
            if (user.getUsername().toLowerCase().equals(name)) {
                return user;
            }
        }

        return null;
    }

    @RequestMapping(value = "/finduser")
    public User findUser(
            @RequestParam(value = "user") String name) {
        return userService.findUserByUserName(name);

    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> add(
            @Valid @RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> del(@PathVariable("id") int id) {

        try {
            userService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/sum")
    public Sum sum(
            @RequestParam(value = "a") int a,
            @RequestParam(value = "b") int b) {

        return new Sum(a, b);
    }
}



