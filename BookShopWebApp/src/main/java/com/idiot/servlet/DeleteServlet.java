package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String query="delete from book.bookdata where id=?";
       
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root & password=root");
			
			PreparedStatement pstmt=con.prepareStatement(query);
			pstmt.setInt(1, id);
			
			int count=pstmt.executeUpdate();
			if(count==1)
			{
				out.println("<h2>Record is Deleted Sucessfully");
			}
			else
			{
				out.println("<h2>Record is Not  Deleted Sucessfully");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2>");
		}
		out.println("<center><a href=\"Home.html\">Home</a></center>");
		
		out.println("<br>");
		out.println("<center><a href=\"listBook\"> Book List </a></center>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException 
	{
		doGet(req,resp);
	}
}
