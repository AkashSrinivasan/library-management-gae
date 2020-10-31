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
 * Servlet implementation class ReturnBook
 */
@WebServlet("/returnBook")
public class ReturnBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnBook() {
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
		response.getWriter().print("<style>"
				+ "table, th, td {\r\n"
				+ "  border: 1px solid black;\r\n"
				+ "}"
				+ "</style>");
		User user = (User) getServletContext().getAttribute("user");
		List<Book> books = ofy().load().type(Book.class).list();
		int barrowedBookCount = 0;
		for(Book book:books) {
			try {
				if(book.barrowerId == user.getId()) {
					barrowedBookCount++;
				}
			}catch(NullPointerException nullPointerExc){
				response.sendRedirect("index.html");
			}
			
		}
		if(books != null && barrowedBookCount>0 ) {
			response.getWriter().print("<p> Your book list</p>");
			response.getWriter().print("<table>\r\n"
					+ "			  <tr>\r\n"
					+ "			    <th>Id</th>\r\n"
					+ "			    <th>Valid till</th>\r\n"
					+ "			  </tr>");
			books.forEach(i -> {
				try {
					if(i.barrowerId == user.getId()) {
						response.getWriter().print("<tr><td>"+i.id+"</td>");
						response.getWriter().println("<td>"+ ChronoUnit.DAYS.between(LocalDateTime.now(), i.getDueDate())+"</td></tr>");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			response.getWriter().print("</table>");
			
			response.getWriter().print("<form action=\"returnBook\" method=\"POST\">"
					+ "Enter the book id :<input type=\"text\" placeholder=\"Enter book Id\" name=\"bookid\" required>"
					+ "<button type=\"submit\">remove</button> "
					+ "</form>");
		}else {
			response.getWriter().print("<p>no books in you stack</p>");
			response.getWriter().println("<a href=\"home\">back</a>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		Long bookId = (long) Integer.parseInt(request.getParameter("bookid"));
		Book book = ofy().load().type(Book.class).id(bookId).now();
		if(book != null) {
			book.barrowerId = (long) 0;
			book.borrowedOn = null;
			book.dueDate = null;
			ofy().save().entity(book).now();
			System.out.println("repleced sucessfully");
		}else {
			System.out.println("please enter the vaild book id");
		}
		response.sendRedirect("home");
	}

}
