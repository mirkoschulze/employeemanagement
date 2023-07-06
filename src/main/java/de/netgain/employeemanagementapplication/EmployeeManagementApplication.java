package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.model.Address;
import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * Main entry point for a Spring web application.
 * <p>
 * Includes Spring configuration for JPA/JSF usage and JPA operations for
 * development purposes.
 *
 * @author MirkoSchulze
 */
@SpringBootApplication
public class EmployeeManagementApplication extends SpringBootServletInitializer {
//TODO addresses, apache commons strings = null safe, apache commons random string utils
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner departments(DepartmentRepository depRepo, EmployeeRepository empRepo) {
        return (args) -> {
            List<String> streets = Arrays.asList("");
            List<String> cities = Arrays.asList("");
            List<String> countries = Arrays.asList("");
            
            depRepo.save(new Department("Geschäftsführung", new Address("Komm-Puter-Straße", "7", "12345", "Prozess-Ort", "IT-alien")));
            depRepo.save(new Department("Produktion"));
            depRepo.save(new Department("Entwicklung"));

            List<Department> departments = depRepo.findAll();
            Random randy = new Random();

            empRepo.save(new Employee("Jack", "Bauer", departments.get(randy.nextInt(3)),
                    new Address("Bauerstreet", "19", "09875", "Bauertown", "Bauerland")));
            empRepo.save(new Employee("Cloe", "o'Brian", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("Kim", "Bauer", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("David", "Palmer", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("Michelle", "Dessler", departments.get(randy.nextInt(3))));
        };
    }

}
