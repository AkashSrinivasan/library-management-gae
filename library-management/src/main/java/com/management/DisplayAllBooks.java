package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayAllBooks
 */
@WebServlet("/displayAllBooks")
public class DisplayAllBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllBooks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userId") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		List<Book> allBooks = ofy().load().type(Book.class).list();
		if(allBooks != null) {
			response.getWriter().print("<p>All Book List</p>");
			for (Book book : allBooks) {
				response.getWriter().println("<p>ID = "+book.id+"</p>");
				response.getWriter().println("<p>Name = "+book.name+"</p>");
				response.getWriter().println("<p>BarrowerId = "+book.barrowerId+"</p>");
				response.getWriter().println("<p>valid for = "+book.name+"</p>");
			}
		}else {
			response.getWriter().print("<p>No Book in library</p>");
		}
		
		response.getWriter().println("<a href=\"adminHome\">back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
