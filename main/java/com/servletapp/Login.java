package com.servletapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")

public class Login extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		ServletContext context = getServletConfig().getServletContext();
		String driver = context.getInitParameter("driver");
		String username = context.getInitParameter("username");
		String password = context.getInitParameter("password");
		String url = context.getInitParameter("url");

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, username, password);
			PreparedStatement stmt = con.prepareStatement("INSERT INTO userlog (username) values (?)");
			stmt.setString(1, request.getParameter("uname"));
			stmt.executeUpdate();
			con.close();
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}catch(Exception e)
		{
			out.print("Exception " +e);
		}
		
		
		out.close();
	}
}
