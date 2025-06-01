package techin.lt.Tour.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techin.lt.Tour.dto.UserMapper;
import techin.lt.Tour.dto.CreateUserRequest;
import techin.lt.Tour.exception.EmailAlreadyExistsException;
import techin.lt.Tour.exception.UsernameAlreadyExistsException;
import techin.lt.Tour.model.Role;
import techin.lt.Tour.model.User;
import techin.lt.Tour.repository.RoleRepository;
import techin.lt.Tour.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User save(User user) {
        return userRepository.save(user);
    }

    public User saveUser(CreateUserRequest dto) throws IOException {
        if (userRepository.existsByEmail(dto.email())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        if (userRepository.existsByUsername(dto.username())){
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        Role roleUser = roleRepository.findByName("USER").orElseThrow();

        User newUser = userMapper.toUser(dto);

        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRoles(Set.of(roleUser));

        return userRepository.save(newUser);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
