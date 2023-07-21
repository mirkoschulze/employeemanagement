package de.netgain.employeemanagementapplication.config;

import jakarta.faces.context.FacesContext;
import jakarta.faces.webapp.FacesServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration for JSF.
 *
 * @author MirkoSchulze
 */
@Configuration
public class JsfConfig implements ServletContextAware, WebMvcConfigurer, WebApplicationInitializer {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        servletContext.setAttribute(com.sun.faces.RIConstants.FACES_INITIALIZER_MAPPINGS_ADDED, Boolean.TRUE);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/welcome.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FacesContext.getCurrentInstance().getApplication().addELResolver(new SpringBeanFacesELResolver());
    }

}
