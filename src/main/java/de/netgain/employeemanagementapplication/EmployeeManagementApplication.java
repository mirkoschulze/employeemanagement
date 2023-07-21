package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
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
    //TODO custom query box

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner prepareDb(EmployeeRepository empRepo, DepartmentRepository depRepo) {
        return (args) -> {
            Generator gen = new Generator();
            gen.prepareDatabase(empRepo, depRepo);
        };
    }

}
