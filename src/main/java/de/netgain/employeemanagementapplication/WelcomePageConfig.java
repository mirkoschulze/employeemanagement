package de.netgain.employeemanagementapplication;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures a welcome page that is loaded if no .xhtml is specified.
 *
 * @author MirkoSchulze
 */
@Configuration
public class WelcomePageConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/welcome.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
