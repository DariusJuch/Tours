package techin.lt.Tour.dto;

import org.springframework.stereotype.Component;
import techin.lt.Tour.dto.user.CreateUserRequest;
import techin.lt.Tour.model.User;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
      return new UserResponse(
              user.getId(),
              user.getUsername(),
              user.getEmail(),
              user.getRoles()
      );
    }

    public User toUser (CreateUserRequest dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }
}
