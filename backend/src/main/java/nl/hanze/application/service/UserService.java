package nl.hanze.application.service;


import nl.hanze.application.entities.User;

import java.util.List;

public interface UserService {
    void deleteUser(int id);
    User save(User user);
    List<User> findAll();
    User findUserByUserName(String name);
}
