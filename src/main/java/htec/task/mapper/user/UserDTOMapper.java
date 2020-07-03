package htec.task.mapper.user;

import htec.task.model.User;
import htec.task.web.dto.user.UserPostDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public User toEntity(UserPostDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
}
