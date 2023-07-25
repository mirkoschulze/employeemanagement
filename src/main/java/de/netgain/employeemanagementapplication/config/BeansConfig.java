package de.netgain.employeemanagementapplication.config;

import de.netgain.employeemanagementapplication.security.UserDetailsServiceImpl;
import de.netgain.employeemanagementapplication.service.DepartmentService;
import de.netgain.employeemanagementapplication.service.DepartmentServiceImpl;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import de.netgain.employeemanagementapplication.service.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration for Spring Beans.
 *
 * @author MirkoSchulze
 */
@Configuration
public class BeansConfig {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

    @Bean
    public DepartmentService departmentService() {
        return new DepartmentServiceImpl();
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
