package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.service.DepartmentService;
import de.netgain.employeemanagementapplication.service.DepartmentServiceImpl;
import de.netgain.employeemanagementapplication.service.EmployeeService;
import de.netgain.employeemanagementapplication.service.EmployeeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
