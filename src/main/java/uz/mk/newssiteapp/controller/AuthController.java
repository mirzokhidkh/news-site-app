package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.LoginDto;
import uz.mk.newssiteapp.payload.RegisterDto;
import uz.mk.newssiteapp.security.JwtProvider;
import uz.mk.newssiteapp.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse response = authService.register(registerDto);
        return ResponseEntity.status(response.isStatus() ? 201 : 409).body(response);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        User user = (User) authentication.getPrincipal();
        String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
//        ApiResponse response = authService.login(loginDto);
//        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
        return ResponseEntity.ok(token);
    }

}
