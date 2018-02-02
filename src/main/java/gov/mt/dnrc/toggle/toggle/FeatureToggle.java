package gov.mt.dnrc.toggle.toggle;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(FeatureCondition.class)
public @interface FeatureToggle {
    String feature();

    boolean expectedToBeOn() default true;
}
