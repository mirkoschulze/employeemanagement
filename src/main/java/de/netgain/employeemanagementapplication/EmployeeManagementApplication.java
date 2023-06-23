package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.model.Employee;
import de.netgain.employeemanagementapplication.repository.EmployeeRepository;
import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

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
    
    //TODO raus
    @Bean
        public CommandLineRunner demo(EmployeeRepository repo){
            return (args) -> {
                repo.save(new Employee("Jack", "Bauer"));
                repo.save(new Employee("Cloe", "o'Brian"));
                repo.save(new Employee("Kim", "Bauer"));
                repo.save(new Employee("David", "Palmer"));
                repo.save(new Employee("Michelle", "Dessler"));
            };
        }
        
        //TODO javadoc
}
