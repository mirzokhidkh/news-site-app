package uz.mk.newssiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.entity.Role;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.UserDto;
import uz.mk.newssiteapp.repository.RoleRepository;
import uz.mk.newssiteapp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public ApiResponse getOneById(Long id) {
        if (!userRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        User user = userRepository.findById(id).get();
        return new ApiResponse("User with " + id, true, user);
    }

    public ApiResponse add(UserDto userDto) {
        Role role = roleRepository.findById(userDto.getRoleId()).get();
        User user = new User(
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                role,
                true
        );
        userRepository.save(user);

        return new ApiResponse("user saved", true);
    }

    public ApiResponse edit(Long id, UserDto userDto) {
        User editinguser = userRepository.findById(id).get();
        editinguser.setFullName(userDto.getFullName());
        editinguser.setUsername(userDto.getUsername());
        editinguser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findById(userDto.getRoleId()).get();
        editinguser.setRole(role);
        userRepository.save(editinguser);

        return new ApiResponse("User edited", true);
    }

    public ApiResponse deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        userRepository.deleteById(id);
        return new ApiResponse("User edited", true);
    }
}
