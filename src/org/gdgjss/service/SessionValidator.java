package org.gdgjss.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * This class is for session validation of user handled by springMVC pre-handler-intercepter.
 * @author Tilhari
 *
 */
public class SessionValidator extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
        if (((HandlerMethod)handler).getBean() instanceof SessionControlled) {
            if (session == null || session.getAttribute("SESSION") == null) {
                throw new Exception("SESSION EXPIRED, LOG IN AGAIN");
            }
        }
        
        return true;
    }

}
