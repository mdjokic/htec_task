package htec.task.service;

import htec.task.model.User;

public interface UserService {


    User findByUsername(String username);
}
