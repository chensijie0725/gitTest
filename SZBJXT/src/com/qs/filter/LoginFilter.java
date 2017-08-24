package com.qs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.model.User;

 

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest paramServletRequest,
			ServletResponse paramServletResponse, FilterChain paramFilterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) paramServletRequest;
		HttpServletResponse response = (HttpServletResponse) paramServletResponse;
		
		String path = request.getRequestURI();	 
		User user = (User) request.getSession().getAttribute("userInfo");
		if("".equals(user)||null==user){	
			if(!path.equals("/user/Login.htm") ){
				response.sendRedirect("/login.jsp"); 
				return;
			}		 
		}			
		paramFilterChain.doFilter(request, response);
		return;

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
