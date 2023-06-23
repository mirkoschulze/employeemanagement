package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.service.EmployeeService;
import de.netgain.employeemanagementapplication.service.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }

}
