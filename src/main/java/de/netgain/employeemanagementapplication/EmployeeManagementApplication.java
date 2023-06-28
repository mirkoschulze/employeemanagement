package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.model.Department;
import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.DepartmentRepository;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;
import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

/**
 * Main entry point for a Spring web application.
 * <p>
 * Includes configuration for JSF usage and JPA operations for development purposes.
 *
 * @author MirkoSchulze
 */
@SpringBootApplication
public class EmployeeManagementApplication extends SpringBootServletInitializer implements ServletContextAware {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
    }

    @Bean
    public CommandLineRunner departments(DepartmentRepository depRepo, EmployeeRepository empRepo) {
        return (args) -> {
            depRepo.save(new Department("Geschäftsführung"));
            depRepo.save(new Department("Produktion"));
            depRepo.save(new Department("Entwicklung"));

            List<Department> departments = depRepo.findAll();
            Random randy = new Random();

            empRepo.save(new Employee("Jack", "Bauer", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("Cloe", "o'Brian", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("Kim", "Bauer", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("David", "Palmer", departments.get(randy.nextInt(3))));
            empRepo.save(new Employee("Michelle", "Dessler", departments.get(randy.nextInt(3))));
        };
    }

}
