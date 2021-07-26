package com.example.userservice.controller;


import com.example.userservice.vo.Greeting;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private Environment env;
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, Greeting greeting) {
        this.env = env;
        this.greeting = greeting;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's Working in User Service"
                + ", port(local.server.port)=" + env.getProperty("local.server.port")
                + ", port(server.port)=" + env.getProperty("server.port")
                + ", token secret=" + env.getProperty("token.secret")
                + ", token expiration time=" + env.getProperty("token.expiration_time"));
    }

    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage(); // @Value 사용
    }

//    @PostMapping("/users")
//    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//
//        UserDto userDto = mapper.map(user, UserDto.class);
//        userService.createUser(userDto);
//
//        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
//    }
//
//    @GetMapping("/users")
//    public ResponseEntity<List<ResponseUser>> getUsers() {
//        Iterable<UserEntity> userList = userService.getUserByAll();
//
//        List<ResponseUser> result = new ArrayList<>();
//        userList.forEach(v -> {
//            result.add(new ModelMapper().map(v, ResponseUser.class));
//        });
//
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
//
//    @GetMapping("/users/{userId}")
//    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {
//        UserDto userDto = userService.getUserByUserId(userId);
//
//        ResponseUser returnValue = new ModelMapper().map(userDto, ResponseUser.class);
//
//        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
//    }
}