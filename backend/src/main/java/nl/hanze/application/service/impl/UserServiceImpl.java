package nl.hanze.application.service.impl;

import nl.hanze.application.domain.User;
import nl.hanze.application.repositories.UserRepository;
import nl.hanze.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserByUserName(String name) {
        return userRepository.findByUsernameEquals(name);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public boolean checkUserNamePasswordIsCorrect(String username, String password) {
        return userRepository.findByUsernameEqualsAndPasswordEquals(username, password) != null;
    }

    public User findUserBycredential(String username, String password){
        return userRepository.findByUsernameEqualsAndPasswordEquals(username, password);
    }




}
