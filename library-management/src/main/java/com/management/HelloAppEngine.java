package com.management;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.cloud.datastore.NullValue;


@WebServlet(
	
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
//    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
////    Book book  = new Book(121,"rich dad");
//    
////    Car car = new Car("123123", "red", book);
////    System.out.println(car.arr);
//    
////    ofy().save().entity(car).now();
////    
//    Car c = ofy().load().type(Car.class).id("888").now();
//    System.out.println(c.vin);
//    ArrayList<Book> ar = c.book;
//    for(int i=0;i<ar.size();i++) {
//    	Book b = ar.get(i);
////    	System.out.println(book.id);
//    }
////    System.out.println(c.books);
////    ArrayList<String> boo = c.arr;
//    
////    for(int i=0;i<boo.size();i++) {
////    	System.out.println(boo.get(i));
////    	
////    }
//   

    response.getWriter().print("Hello App Engine!\r\n");
    

  }
}