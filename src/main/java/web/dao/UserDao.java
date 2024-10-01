package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    User getUserById(Long id);
    void remove(Long id);
    List<User> getAllUsers();
}
