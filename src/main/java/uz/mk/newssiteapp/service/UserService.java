package uz.mk.newssiteapp.service;

import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.UserDto;

@Service
public class UserService {

    public ApiResponse addUser(UserDto userDto) {

        return new ApiResponse("",true);
    }
}
