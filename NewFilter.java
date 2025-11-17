package com.practice.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class NewFilter implements Filter {

	private final static Logger logger=LoggerFactory.getLogger(NewFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		logger.info("Filter Intercept request [New Filter] "+req.getRequestURI());
		chain.doFilter(request, response);	
		
	}

	

}
