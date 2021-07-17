package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.mk.newssiteapp.aop.CheckPermission;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.UserDto;
import uz.mk.newssiteapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping
    public HttpEntity<?> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse response = userService.getOneById(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody UserDto userDto) {
        ApiResponse response = userService.add(userDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }


    //    @PreAuthorize(value = "hasAuthority('EDIT_User')")
    @CheckPermission(permission = "EDIT_USER")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,
                              @Valid @RequestBody UserDto userDto) {
        ApiResponse response = userService.edit(id, userDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = userService.deleteById(id);
        return ResponseEntity.status(response.isStatus() ? 204 : 409).body(response);
    }

}
