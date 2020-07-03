package htec.task.web.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthPostDTO {

    private String username;

    private String password;

    public AuthPostDTO() { }
}
