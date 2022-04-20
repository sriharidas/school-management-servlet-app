package com.servletapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class Signup extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		String driver = context.getInitParameter("driver");
		String url = context.getInitParameter("url");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO accounts (username, first_name, last_name, password) values ( ?, ?,?,?)");
			stmt.setString(1, request.getParameter("uname"));
			stmt.setString(2, request.getParameter("fname"));
			stmt.setString(3, request.getParameter("lname"));
			stmt.setString(4, request.getParameter("password"));
			
			stmt.executeUpdate();
			con.close();
			out.println("Account created");
		}catch(Exception e)
		{
			out.println(e);
		}
	}
}