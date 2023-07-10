package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.model.Address;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for a Spring application.
 *
 * @author MirkoSchulze
 */
@SpringBootApplication
public class EmployeeManagementApplication extends SpringBootServletInitializer {

//TODO apache commons strings = null safe
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    /**
     * Only during development.
     */
    @Bean
    public CommandLineRunner departments(DepartmentRepository depRepo, EmployeeRepository empRepo) {
        return (args) -> {
            Generator gen = new Generator();
            gen.generateDatabase(depRepo, empRepo);
        };
    }

}
