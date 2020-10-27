package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/barrowBook")
public class BarrowBook extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("userId") == null ) {
			response.sendRedirect("index.html");
		}
		response.setHeader("Cache-Control","no-cache, no-store, must-revalidate" );
		response.setContentType("text/html");
		List<Book> allBooks = ofy().load().type(Book.class).list();
		int availableBookCount = 0;
		for(Book book:allBooks) {
			if(book.barrowerId == 0) {
				availableBookCount++;
			}
		}
		if(allBooks==null || availableBookCount == 0) {
			response.getWriter().println("<h1>No book are available right now.!</h1>");
			response.getWriter().println("<a href=\"home\">back</a>");
		}
		else {
				response.getWriter().print("<p>only these book available right now !");
				int[] bookCount = {0};
				allBooks.stream().forEach((i)-> {
					if(i.barrowerId ==0) {
						try {
							response.getWriter().print("<p>Book - "+ (bookCount[0]+1)+"</p>\r\n");
							bookCount[0] = bookCount[0]+1;
							response.getWriter().print("<p>----------------------------------------</p> \r\n");
							response.getWriter().print("<p>Book id = "+i.id+"</p>\r\n");
							response.getWriter().print("<p>Book Name = "+i.name+"</p>\r\n\r\n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
				});
				response.getWriter().print("<form action=\"barrowBook\" method=\"POST\">"
						+ "Enter the book id :<input type=\"text\" placeholder=\"Enter book Id\" name=\"bookid\" required>"
						+ "<button type=\"submit\">add</button> "
						+ "</form>");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) getServletContext().getAttribute("user");
		Long bookId = (long) Integer.parseInt(request.getParameter("bookid"));
		Book book = ofy().load().type(Book.class).id(bookId).now();
		book.barrowerId = user.getId();
		LocalDateTime currentDate = LocalDateTime.now();
		book.borrowedOn = currentDate + "";
		LocalDateTime currentPlusFifteen = currentDate.plusDays(15);
		book.dueDate =currentPlusFifteen+"";
		ofy().save().entity(book).now();
		System.out.println("book barrowed sucessfully.!");
		response.sendRedirect("home");
	}
}