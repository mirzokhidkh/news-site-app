package uz.mk.newssiteapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.mk.newssiteapp.entity.enums.Permission;
import uz.mk.newssiteapp.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails{

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled;

    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Permission> permissionList = this.role.getPermissionList();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Permission permission : permissionList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }

        return grantedAuthorities;
    }

}
