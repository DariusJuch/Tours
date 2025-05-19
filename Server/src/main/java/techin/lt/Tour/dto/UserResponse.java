package techin.lt.Tour.dto;

import techin.lt.Tour.model.Role;

import java.util.Set;

public record UserResponse(
        long id,
        String username,
        String email,
        Set<Role> roles
) {
}
