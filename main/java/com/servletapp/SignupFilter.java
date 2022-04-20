package com.servletapp;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/signup")
public  class SignupFilter implements Filter {
	public void init(FilterChain arg) throws ServletException {} 
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		if(!password.equals(cpassword))
		{
			RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
			request.setAttribute("message", "Password Doesn't match");
			rd.forward(request, response);
		}
		else
		{
			chain.doFilter(request, response);
		}
		
			
	}
	public void destroy() {}

}
