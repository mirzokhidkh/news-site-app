package uz.mk.newssiteapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionType;
import org.springframework.security.core.GrantedAuthority;
import uz.mk.newssiteapp.entity.enums.Permission;
import uz.mk.newssiteapp.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbsEntity {
    @Column(unique = true, nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

    @Column(columnDefinition = "text",length = 500)
    private String description;

}
