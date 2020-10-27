package com.management;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayBook
 */
@WebServlet("/displayBook")
public class DisplayBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("userId") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		User user = (User) getServletContext().getAttribute("user");
		List<Book> books = ofy().load().type(Book.class).list();
		
		int barrowedBookCount = 0;
		for(Book book:books) {
			if(book.barrowerId == user.getId()) {
				barrowedBookCount++;
			}
		}
		if(books != null && barrowedBookCount>0) {
			String bookAsString = books.size() >1 ? "books":"book";
			response.getWriter().print("<p>You have borrowed "+books.size()+" "+ bookAsString+"</p>");
			books.forEach(book -> {
				try {
					if(book.barrowerId == user.getId()) {
						response.getWriter().print("<p>Book Name = "+book.name+"</p>");
						response.getWriter().print("<p>Return the book before "+ book.getBorrowedOn().toLocalDate()+"</p>" );
						response.getWriter().print("<p>Valid only for "+ ChronoUnit.DAYS.between(LocalDateTime.now(), book.getDueDate())+" days</p>");
						response.getWriter().print("</br>");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			});
		}else {
			response.getWriter().println("<h1>No books to display..!</h1>");
		}
		response.getWriter().println("<a href=\"home\">back</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
