package nl.hanze.application.service;


import nl.hanze.application.domain.Role;
import nl.hanze.application.domain.User;

import java.util.List;

public interface UserService {
    void deleteById(int id);

    User save(User user);

    List<User> findAll();

    User findUserByUserName(String name);

    boolean checkUserNamePasswordIsCorrect(String username, String password);

    public User findUserBycredential(String username, String password);

    List<Role> findAllRoles();
}
