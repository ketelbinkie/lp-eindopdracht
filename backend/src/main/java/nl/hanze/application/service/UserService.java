package nl.hanze.application.service;


import nl.hanze.application.entities.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
}
