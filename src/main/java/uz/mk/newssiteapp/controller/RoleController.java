package uz.mk.newssiteapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.mk.newssiteapp.aop.CheckPermission;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.RoleDto;
import uz.mk.newssiteapp.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PreAuthorize(value = "hasAuthority('VIEW_USERS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        ApiResponse response = roleService.getOneById(id);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> add(@Valid @RequestBody RoleDto roleDto) {
        ApiResponse response = roleService.add(roleDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }


    //    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @CheckPermission(permission = "EDIT_ROLE")
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Long id,
                              @Valid @RequestBody RoleDto roleDto) {
        ApiResponse response = roleService.edit(id, roleDto);
        return ResponseEntity.status(response.isStatus() ? 200 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {
        ApiResponse response = roleService.deleteById(id);
        return ResponseEntity.status(response.isStatus() ? 204 : 409).body(response);
    }

}
