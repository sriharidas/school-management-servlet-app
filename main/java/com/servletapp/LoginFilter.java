package com.servletapp;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/login")
public class LoginFilter implements Filter{
	
	FilterConfig config;
	public void init(FilterConfig config)
	{
		this.config = config;
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		ServletContext context = config.getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String usernmae = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		
		PrintWriter out = response.getWriter();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, usernmae, password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts where username = \"" + request.getParameter("uname") + "\"");
			rs.next();
			
			try {
				rs.getString(1);
				chain.doFilter(request, response);
			}
			catch(SQLException s)
			{
				
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				request.setAttribute("message", "Username does not exist!");
				rd.forward(request, response);
			}
		}catch(Exception e)
		{
			out.println("Exception : "+ e);
		}
	}
	
	
}
