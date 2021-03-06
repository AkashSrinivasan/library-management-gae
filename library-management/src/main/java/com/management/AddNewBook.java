package com.management;


import static com.googlecode.objectify.ObjectifyService.ofy;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddNewBook
 */
@WebServlet("/addNewBook")
public class AddNewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewBook() {
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
		response.getWriter().print("<form action=\"addNewBook\" align=\"center\" method=\"POST\">\r\n"
				+ "            <label>Book Id : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Book Id\" name=\"bookId\" required><br>\r\n"
				+ "            <label>Book Name : </label> \r\n"
				+ "            <input type=\"text\" placeholder=\"Enter Book Name\" name=\"bookName\" required><br>\r\n"
				+ "            <button type=\"submit\">Add</button><br> \r\n"
				+ "        </div> \r\n"
				+ "    </form><br>"
				+ "		<a href=\"adminHome\">Cancel</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book(Integer.parseInt(request.getParameter("bookId")), request.getParameter("bookName"), 0, null, null);
		ofy().save().entity(book).now();
		System.out.println("book added sucessfully");
		response.sendRedirect("adminHome");
	}

}
