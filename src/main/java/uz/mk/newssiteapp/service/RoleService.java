package uz.mk.newssiteapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.mk.newssiteapp.entity.Role;
import uz.mk.newssiteapp.payload.ApiResponse;
import uz.mk.newssiteapp.payload.RoleDto;
import uz.mk.newssiteapp.payload.UserDto;
import uz.mk.newssiteapp.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public ApiResponse getOneById(Long id) {
        if (!roleRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        Role role = roleRepository.findById(id).get();
        return new ApiResponse("Role with " + id, true, role);
    }

    public ApiResponse add(RoleDto roleDto) {
        if (roleRepository.existsByName(roleDto.getName())) {
            return new ApiResponse("Role with such name already exists", false);
        }

        Role role = new Role(
                roleDto.getName(),
                roleDto.getPermissionList(),
                roleDto.getDescription()
        );
        roleRepository.save(role);

        return new ApiResponse("Role saved", true);
    }

    public ApiResponse edit(Long id, RoleDto roleDto) {
        if (roleRepository.existsByNameAndIdNot(roleDto.getName(), id)) {
            return new ApiResponse("Role with such name already exists", false);
        }

        Role editingRole = roleRepository.findById(id).get();
        editingRole.setName(roleDto.getName());
        editingRole.setDescription(roleDto.getDescription());
        editingRole.setPermissionList(roleDto.getPermissionList());
        roleRepository.save(editingRole);

        return new ApiResponse("Role edited", true);
    }

    public ApiResponse deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            return new ApiResponse("Id " + id + " not found", false);
        }
        roleRepository.deleteById(id);
        return new ApiResponse("Role edited", true);
    }
}
