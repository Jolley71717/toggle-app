package gov.mt.dnrc.toggle.config;

import gov.mt.dnrc.toggle.toggle.FeatureInterceptor;
import gov.mt.dnrc.toggle.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ToggleService toggleService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FeatureInterceptor(toggleService));
        super.addInterceptors(registry);
    }
}
