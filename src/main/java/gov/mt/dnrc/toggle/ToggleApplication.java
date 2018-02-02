package gov.mt.dnrc.toggle;

import gov.mt.dnrc.toggle.toggle.FeatureInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootApplication
public class ToggleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToggleApplication.class, args);
	}
}
