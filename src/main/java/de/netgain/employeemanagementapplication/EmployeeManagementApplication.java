package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import de.netgain.employeemanagementapplication.security.Role;
import de.netgain.employeemanagementapplication.security.RoleRepository;
import de.netgain.employeemanagementapplication.security.User;
import de.netgain.employeemanagementapplication.security.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Main entry point for a Spring application.
 *
 * @author MirkoSchulze
 */
@SpringBootApplication
public class EmployeeManagementApplication extends SpringBootServletInitializer {
    //TODO custom query box

//    public static void main(String[] args) {
//        SpringApplication.run(EmployeeManagementApplication.class, args);
//    }
    @Bean
    public CommandLineRunner prepareDb(EmployeeRepository empRepo, DepartmentRepository depRepo, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        return (args) -> {
            Generator gen = new Generator();
            gen.prepareDatabase(empRepo, depRepo);

            roleRepo.save(new Role("ROLE_ADMIN"));
            roleRepo.save(new Role("ROLE_USER"));

            User user = new User("admin", encoder.encode("123"));
            user.setRoles(new HashSet<>(roleRepo.findAll()));

            userRepo.save(user);

            user = new User("user", encoder.encode("123"));
            user.setRoles(new HashSet<>(Arrays.asList(roleRepo.getRoleByName("ROLE_USER").get())));

            userRepo.save(user);
        };
    }

}
