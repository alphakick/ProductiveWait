package com.stallion.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	final static Logger logger = Logger.getLogger(AuthenticationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
 
		logger.info("Interceptor: Pre-handle");
		return true;
 
		// Avoid a redirect loop for some urls
//		if( !request.getRequestURI().equals("/sample-interc/") &&
//		    !request.getRequestURI().equals("/sample-interc/login.do") &&
//		    !request.getRequestURI().equals("/sample-interc/login.failed"))
//		  {
//			  LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
//		   if(userData == null)
//		   {
//		    response.sendRedirect("/sample-interc/");
//		    return false;
//		   }   
//		  }
//		  return true;
	}

}
