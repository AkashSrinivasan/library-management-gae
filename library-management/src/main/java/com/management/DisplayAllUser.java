package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/displayAllUser")
public class DisplayAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("userId") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		List<Student> allStudent = ofy().load().type(Student.class).list();
		if(allStudent == null) {
			response.getWriter().println("<p>Student List is empty</p>");
		}else {
			response.getWriter().println("<p>Student List</p>");
			for(Student student:allStudent) {
				response.getWriter().println("<p>Student name = "+student.getName()+"</p>");
				response.getWriter().println("<p>Student id = "+student.getId()+"</p><br>");
			}
		}
		List<Staff> allStaff = ofy().load().type(Staff.class).list();
		if(allStaff == null) {
			response.getWriter().println("<p>Student List is empty</p>");
		}else {
			response.getWriter().println("<p>Student List</p>");
			for(Staff staff:allStaff) {
				response.getWriter().println("<p>Student name = "+staff.getName()+"</p>");
				response.getWriter().println("<p>Student id = "+staff.getId()+"</p><br>");
			}
		}
		response.getWriter().println("<a href=\"adminHome\">back</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}