package htec.task.service;

import htec.task.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
