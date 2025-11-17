package com.practice.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class StudentInterceptor implements HandlerInterceptor{
  
	public static final Logger logger=LoggerFactory.getLogger(StudentInterceptor.class);
			
	   @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		   logger.info("PreHandle Interceptor Request"+request.getRequestURI());
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	   
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("PostHandle Interceptor Request"+request.getRequestURI());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("AfterCompletion Interceptor Request"+request.getRequestURI());
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
