package de.netgain.employeemanagementapplication;

import de.netgain.employeemanagementapplication.service.EmployeeService;
import de.netgain.employeemanagementapplication.service.EmployeeServiceImpl;
import jakarta.faces.webapp.FacesServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Bean registration.
 *
 * @author MirkoSchulze
 */
@Configuration
public class BeansConfig {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl();
    }
    
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
//    }

}
