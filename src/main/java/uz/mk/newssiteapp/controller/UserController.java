package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.RegisterDto;
import uz.mk.newssiteapp.payload.UserDto;
import uz.mk.newssiteapp.service.AuthService;
import uz.mk.newssiteapp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody UserDto userDto) {
        ApiResponse response = userService.addUser(userDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }
}
