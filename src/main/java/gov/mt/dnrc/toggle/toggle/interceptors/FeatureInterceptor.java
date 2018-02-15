package gov.mt.dnrc.toggle.toggle.interceptors;

import gov.mt.dnrc.toggle.toggle.interceptors.annotations.FeatureToggle;
import gov.mt.dnrc.toggle.toggle.service.spi.IToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Feature Interceptor is a class that will check to see if a feature is enabled before allowing
 * the user to use a feature. Implements the Spring servlet HandlerInterceptor class to enable the handling of
 * the feature toggle annotations.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class FeatureInterceptor implements HandlerInterceptor {

    private final IToggleService toggleService;

    @Autowired
    public FeatureInterceptor(IToggleService toggleService) {
        this.toggleService = toggleService;
    }

    /**
     * Handles to see if the request method is enabled.
     * If it does not find the feature is enabled, it will return a 404 error.
     *
     * @param httpServletRequest Servlet Request Object
     * @param httpServletResponse Servlet Response Object
     * @param handler Handler Method used to check the method class.
     * @return returns true if the feature is enabled or if the feature does not contain a toggle.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        FeatureToggle methodAnnotation = handlerMethod.getMethodAnnotation(FeatureToggle.class);

        // Looks to see if the module requested is a toggleable feature.
        if (methodAnnotation == null ||
            toggleService.isModuleEnabled(methodAnnotation.feature()) == null ||
            methodAnnotation.expectedToBeOn() == toggleService.isModuleEnabled(methodAnnotation.feature())) {
            return true;
        }

        // Return the user to a 404 not found page.
        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }

    /**
     * Post handler, since we do not need to handle the post check of a feature toggle there is
     * no need to implement this.
     *
     * @param httpServletRequest Servlet Request Object
     * @param httpServletResponse Servlet Response Object
     * @param handler Handler Method used to check the method class.
     * @param modelAndView Model view object that we can hookup and sent to the next view or use.
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) {
        // Empty Handler
    }

    /**
     * After completion handler, there is no additional task that will need to occur. I may consider adding
     * some logging or some sort of alaysis engine here to see all of the requests to disabled features.
     *
     * @param httpServletRequest Servlet Request Object
     * @param httpServletResponse Servlet Response Object
     * @param handler Handler Method used to check the method class.
     * @param exception exception that may have een thrown during the servlet request process.
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception exception) {
        // Empty Handler
    }
}
