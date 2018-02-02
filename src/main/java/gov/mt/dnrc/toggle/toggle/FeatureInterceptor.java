package gov.mt.dnrc.toggle.toggle;

import gov.mt.dnrc.toggle.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeatureInterceptor implements HandlerInterceptor {

    private final ToggleService toggleService;

    @Autowired
    public FeatureInterceptor(ToggleService toggleService) {
        this.toggleService = toggleService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        FeatureToggle methodAnnotation = handlerMethod.getMethodAnnotation(FeatureToggle.class);

        if (methodAnnotation == null) {
            return true;
        }

        if(toggleService.isModuleEnabled(methodAnnotation.feature()) == null) {
            return true;
        }

        if(methodAnnotation.expectedToBeOn() == toggleService.isModuleEnabled(methodAnnotation.feature())) {
            return true;
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, Exception exception) throws Exception {

    }
}
