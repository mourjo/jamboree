package me.mourjo.jamboree.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put("REQUEST_METHOD", request.getMethod());
        MDC.put("REQUEST_URI", request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.put("RESPONSE_CODE", Integer.toString(response.getStatus()));
        logger.info("Finished processing request");
        MDC.remove("REQUEST_METHOD");
        MDC.remove("REQUEST_URI");
        MDC.remove("RESPONSE_CODE");
    }
}
