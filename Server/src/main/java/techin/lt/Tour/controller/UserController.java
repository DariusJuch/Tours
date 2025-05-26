package techin.lt.Tour.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import techin.lt.Tour.dto.UserMapper;
import techin.lt.Tour.dto.UserResponse;
import techin.lt.Tour.dto.user.CreateUserRequest;
import techin.lt.Tour.model.User;
import techin.lt.Tour.repository.UserRepository;
import techin.lt.Tour.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper){
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponses = new ArrayList<>();

        for (User user : userService.findAllUsers()){
            userResponses.add(userMapper.toUserResponse(user));
        }

        return ResponseEntity.ok(userResponses);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> addUser(@Valid CreateUserRequest dto) throws IOException {

        User newUser = userService.saveUser(dto);
        UserResponse savedUser = userMapper.toUserResponse(newUser);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.id())
                        .toUri())
                .body(savedUser);
    }
}

