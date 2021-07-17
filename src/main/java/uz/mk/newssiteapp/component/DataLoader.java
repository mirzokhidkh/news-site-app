package uz.mk.newssiteapp.component;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.mk.newssiteapp.entity.Role;
import uz.mk.newssiteapp.entity.User;
import uz.mk.newssiteapp.entity.enums.Permission;
import uz.mk.newssiteapp.repository.RoleRepository;
import uz.mk.newssiteapp.repository.UserRepository;
import uz.mk.newssiteapp.utils.AppConstants;

import java.util.Arrays;

import static uz.mk.newssiteapp.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Permission[] permissions = values();
            ;

            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),"owner of the system"));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),"simple user"));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }

}
