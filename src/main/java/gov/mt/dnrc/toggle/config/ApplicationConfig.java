package gov.mt.dnrc.toggle.config;

import gov.mt.dnrc.toggle.toggle.interceptors.FeatureInterceptor;
import gov.mt.dnrc.toggle.toggle.service.spi.IToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Application Java Config. Replaces the spring/web.xml configuration.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    IToggleService toggleService;

    /**
     * WebMvcConfigurerAdapter overridden method to allow the registry of adding the feature interceptor.
     *
     * @param registry InterceptorRegistry used to add interceptors to the web calls.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new FeatureInterceptor(toggleService));
        super.addInterceptors(registry);
    }
}
