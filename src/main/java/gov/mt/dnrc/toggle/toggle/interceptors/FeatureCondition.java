package gov.mt.dnrc.toggle.toggle.interceptors;

import gov.mt.dnrc.toggle.toggle.interceptors.annotations.FeatureToggle;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * Spring Annotation condition that allows the annotation to match annotations on implemented methods and classes
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public class FeatureCondition implements Condition {

    /**
     * Condition matching method that will allow the application to check certain parameters
     * of the feature annotation. We want to look for the name of the feature and if the feature is
     * enabled.
     *
     * @param conditionContext ConditionContext used to check the enabled value of the feature.
     * @param annotatedTypeMetadata AnnotatedTypeMetadata used to grab the attributes of the selected annotation.
     * @return returns a boolean if it found the annotation.
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if(annotatedTypeMetadata.isAnnotated(FeatureToggle.class.getCanonicalName())) {
            Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(FeatureToggle.class.getCanonicalName());

            String feature = (String) annotationAttributes.get("feature");
            boolean expectedToBeOn = Boolean.parseBoolean(String.valueOf(annotationAttributes.get("expectedToBeOn")));
            boolean isOn = Boolean.parseBoolean(conditionContext.getEnvironment().getProperty(feature));
            return expectedToBeOn == isOn;
        }
        return true;
    }
}
