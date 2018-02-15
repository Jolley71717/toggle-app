package gov.mt.dnrc.toggle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Feature Toggle Demonstration/Proof of concept to prove that you can enable and
 * disable features of an application at runtime.
 *
 * @author Brad Villa
 * @version 1.0.0
 */
@SpringBootApplication
public class ToggleApplication {

    /**
     * Spring boot starting point. Use to run the java embedded tomcat instance.
     *
     * @param args Application arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(ToggleApplication.class, args);
	}
}
