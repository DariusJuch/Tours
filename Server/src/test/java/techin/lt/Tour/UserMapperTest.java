package techin.lt.Tour;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import techin.lt.Tour.dto.UserMapper;
import techin.lt.Tour.dto.UserResponse;
import techin.lt.Tour.model.Role;
import techin.lt.Tour.model.User;

import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void shouldMapUserToUserResponse() throws IllegalAccessException, NoSuchFieldException {
        Role role = new Role();
        role.setName("USER");

        User user = new User();
        user.setUsername("darius");
        user.setEmail("dakar@gmail.com");
        user.setPassword("Dakar1234@");
        user.setRoles(Set.of(role));

        Field idField = User.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(user, 1L);

        UserResponse response = userMapper.toUserResponse(user);

        assertEquals(1L, response.id());
        assertEquals("darius", response.username());
        assertEquals("dakar@gmail.com", response.email());
        assertEquals(1, response.roles().size());
    }
}