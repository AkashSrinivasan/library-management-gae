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
		response.getWriter().print("<style>"
				+ "table, th, td {\r\n"
				+ "  border: 1px solid black;\r\n"
				+ "}"
				+ "</style>");
		List<Student> allStudent = ofy().load().type(Student.class).list();
		if(allStudent.size() == 0) {
			response.getWriter().println("<p>Student List is empty</p>");
		}else {
			response.getWriter().println("<p>Student List</p>");
			response.getWriter().print("<table>\r\n"
					+ "			  <tr>\r\n"
					+ "			    <th>Id</th>\r\n"
					+ "			    <th>Name</th>\r\n"
					+ "			  </tr>");
			for(Student student:allStudent) {
				response.getWriter().println("<tr><td>"+student.getId()+"</td>");
				response.getWriter().println("<td>"+student.getName()+"</td></tr>");
			}
			response.getWriter().print("</table>");
		}
		List<Staff> allStaff = ofy().load().type(Staff.class).list();
		System.out.println(allStaff);
		if(allStaff.size() == 0) {
			response.getWriter().println("<p>Staff List is empty</p>");
		}else {
			response.getWriter().println("<p>Staff List</p>");
			response.getWriter().print("<table>\r\n"
					+ "			  <tr>\r\n"
					+ "			    <th>Id</th>\r\n"
					+ "			    <th>Name</th>\r\n"
					+ "			  </tr>");
			for(Staff staff:allStaff) {
				response.getWriter().println("<tr><td>"+staff.getId()+"</td>");
				response.getWriter().println("<td>"+staff.getName()+"</td></tr>");
			}
			response.getWriter().print("</table>");
		}
		response.getWriter().println("<a href=\"adminHome\">back</a>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}