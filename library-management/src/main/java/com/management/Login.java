package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.IllformedLocaleException;

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
	static int id;
   
    public Login() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			id = Integer.parseInt(request.getParameter("id"));
		}catch(NumberFormatException numberFormatExc) {
			response.getWriter().print("<p align=\"center\" style=\"color:red;\">Please, enter your id correctly.!</p>"); 
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		
		String password = request.getParameter("password");
		String userType = request.getParameter("user");
		Cookie cookie = new Cookie("userType", request.getParameter("user"));		
		if(userType.equals("Student")) {
			Student student =null;
			try {
				 student = ofy().load().type(Student.class).id(id).now();
			}catch(IllegalArgumentException illligalArg){
				response.getWriter().print("<p align=\"center\" style=\"color:red;\">It's not valid id</p>"); 
				request.getRequestDispatcher("index.html").forward(request, response);
			}
			
			if(student!=null && student.getPassword().equals(password)) {
				getServletContext().setAttribute("user", student);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", request.getParameter("id"));
				response.addCookie(cookie);
				response.sendRedirect("home");
			}else {
				response.getWriter().print("<p align=\"center\" style=\"color:red;\">The id or password that you've entered doesn't match any account please, <a href=\"signinup\">signinup</a></p>"); 
				request.getRequestDispatcher("index.html").forward(request, response);
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
				response.getWriter().print("<p align=\"center\" style=\"color:red;\">The id or password that you've entered doesn't match any account please, <a href=\\\"signinup\\\">signinup</a></p>"); 
				request.getRequestDispatcher("index.html").forward(request, response);
			}
		}else {
			if(Admin.id == id && Admin.password.equals("root")) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("userId", request.getParameter("id"));
				response.addCookie(cookie);
				response.sendRedirect("adminHome");
				
			}else {
				response.getWriter().print("<p align=\"center\" style=\"color:red;\">The id or password that you've entered doesn't match any account please, <a href=\\\"signinup\\\">signinup</a></p>"); 
				request.getRequestDispatcher("index.html").forward(request, response);
			}	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
