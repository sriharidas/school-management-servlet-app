package com.servletapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

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
import java.sql.*;
@WebFilter("/signup")
public class UserFilter implements Filter{
	FilterConfig config;
	public void init(FilterConfig config) {
		this.config = config;
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException
	{
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		PrintWriter out = response.getWriter();
		out.println("username" + request.getParameter("uname"));
		ServletContext  context = config.getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM accounts where username = \"" + request.getParameter("uname") + "\"");
			
			try {
				rs.next();
				rs.getString(1);
				RequestDispatcher rd = request.getRequestDispatcher("signup.jsp");
				request.setAttribute("message", "username already exists");
				rd.forward(request, response);
			}
			catch(SQLException s)
			{
				chain.doFilter(request, response);
			}
		
		}
		catch(Exception e)
		{
			out.println("exceptin" + e);
		}
	}
	
	public void destroy() {}
}
