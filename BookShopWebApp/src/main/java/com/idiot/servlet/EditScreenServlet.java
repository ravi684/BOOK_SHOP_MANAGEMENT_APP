package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditScreenServlet
 */
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private static final String query="SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM book.bookdata where id=?";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditScreenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			
			out.println("<form action='editurl?id="+id+"' method='post'>");
			
			out.println("<table align='center'>");
			out.println("<tr>");
			out.println("<td>Book Name</td>");
			out.println("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book Edition</td>");
			out.println("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Book Price</td>");
			out.println("<td><input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<td><input type='submit' value='Edit'</td>");
			out.println("<td><input type='reset' value='Cancel'</td>");
			
			out.println("</table>");

			out.println("</form>");
			
		
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("<h1>"+e.getMessage()+"</h2>");
		}
		out.println("<center><a href=\"Home.html\">Home</a></center>");
		
		
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
