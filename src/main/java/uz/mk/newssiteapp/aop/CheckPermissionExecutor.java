package uz.mk.newssiteapp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.exceptions.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {

    @Before(value = "@annotation(checkPermission)")
    public void checkUserPermissionMyMethod(CheckPermission checkPermission) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        GrantedAuthority authority = user.getAuthorities()
                .stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().equals(checkPermission.permission()))
                .findFirst().orElse(null);

        if (authority == null) {
            throw new ForbiddenException(checkPermission.permission(),"Forbidden");
        }
    }
}
