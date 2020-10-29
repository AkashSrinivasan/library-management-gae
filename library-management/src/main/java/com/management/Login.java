package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;

import javax.mail.SendFailedException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String userType = request.getParameter("user");
		Cookie cookie = new Cookie("userType", request.getParameter("user"));
		
//		Student student = ofy().load().type(Student.class).id(id).now();
		
		if(userType.equals("Student")) {
			Student student = ofy().load().type(Student.class).id(id).now();
			System.out.println(student.getName());
			System.out.println(student);
			if(student!=null && student.getPassword().equals(password)) {
				getServletContext().setAttribute("user", student);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", request.getParameter("id"));
//				System.out.println(httpSession.getAttribute("id"));
				response.addCookie(cookie);
				response.sendRedirect("home");
			}else {
				response.sendRedirect("index.html");
			}
		}else if(userType.equals("Staff")) {
			User user = ofy().load().type(Staff.class).id(id).now();
			if(user!=null && user.getPassword().equals(password)) {
				getServletContext().setAttribute("user", user);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", request.getParameter("id"));
				response.addCookie(cookie);
				response.sendRedirect("home");
			}else {
				response.sendRedirect("index.html");
			}
		}else {
			if(Admin.id == id && Admin.password.equals("root")) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", request.getParameter("id"));
				response.addCookie(cookie);
				response.sendRedirect("adminHome");
				
			}else {
				response.sendRedirect("index.html");
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
