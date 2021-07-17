package uz.mk.newssiteapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mk.newssiteapp.entity.enums.Permission;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @NotBlank(message = "Role name mustn't be empty")
    private String name;

    private String description;

    @NotEmpty(message = "Permission list mustn't be empty")
    private List<Permission> permissionList;
}
