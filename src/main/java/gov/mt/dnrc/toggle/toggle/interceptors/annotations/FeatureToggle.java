package gov.mt.dnrc.toggle.toggle.interceptors.annotations;

import gov.mt.dnrc.toggle.toggle.interceptors.FeatureCondition;
import org.springframework.context.annotation.Conditional;
import java.lang.annotation.*;

/**
 * Feature Toggle interface that will allow the application to check
 * if certain methods are enabled/disabled.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(FeatureCondition.class)
public @interface FeatureToggle {

    // The feature name to check with. Think of it as an ID.
    String feature();

    // Boolean value that allows the system to set if its enabled or not.
    boolean expectedToBeOn() default true;
}
