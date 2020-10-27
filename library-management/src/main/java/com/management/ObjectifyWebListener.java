package com.management;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;

@WebListener
public class ObjectifyWebListener implements ServletContextListener {
	
//	static {
//        ObjectifyService.register(Car.class);
//    }

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.init();
    // This is a good place to register your POJO entity classes.
    // ObjectifyService.register(YourEntity.class);
//    ObjectifyService.register(Car.class);
//    ObjectifyService.register(Book.class);
    ObjectifyService.register(Student.class);
    ObjectifyService.register(Staff.class);
    ObjectifyService.register(Book.class);
    
  }

  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }
}