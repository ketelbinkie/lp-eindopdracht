package application.controller;

import application.controller.utils.Sum;
import application.entities.User;
import application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class Controller {

    private final UserRepository userRepository;

    @Autowired
    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/hello")
    public User hello(
            @RequestParam(value = "name") String name) {
        List<User> userList = userRepository.findAll();
        String password;
        for (User user:userList) {
            if (user.getUsername().toLowerCase().equals(name)) {
                return user;
            }
        }

        return null;
    }
//
//    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity add(
//            @Valid @RequestBody User user) {
//        return new ResponseEntity<>(genreService.add(genre), HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/sum")
    public Sum sum(
            @RequestParam(value = "a") int a,
            @RequestParam(value = "b") int b) {

        return new Sum(a, b);
    }
}



