package uz.mk.newssiteapp.aop;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD )
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    String permission();
}
