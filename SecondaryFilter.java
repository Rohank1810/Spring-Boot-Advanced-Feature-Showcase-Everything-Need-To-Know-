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
public class SecondaryFilter implements Filter {

	private final static Logger logger=LoggerFactory.getLogger(SecondaryFilter.class);
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		logger.info("[Secondary Filter]");
		chain.doFilter(request, response);
		
		
	}
}
