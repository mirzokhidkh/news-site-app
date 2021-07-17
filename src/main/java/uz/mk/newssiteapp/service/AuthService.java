package uz.mk.newssiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.exceptions.ResourceNotFoundException;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.RegisterDto;
import uz.mk.newssiteapp.repository.RoleRepository;
import uz.mk.newssiteapp.repository.UserRepository;
import uz.mk.newssiteapp.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())) {
            return new ApiResponse("Passwords didn't match", false);
        }

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ApiResponse("Username already exists", false);
        }
        User user = new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                roleRepository.findByName(AppConstants.USER).
                        orElseThrow(() -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );

        User savedUser = userRepository.save(user);
        return new ApiResponse("Successfully registered", true);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
