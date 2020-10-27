package com.management;

import java.util.ArrayList;

public interface User {
	 public void setId(int id);
	 public void setName(String name);
	 public void setPassword(String password);
//	 public void setBook(Long bookId);
	 public long getId();
	 public String getName();
	 public String getPassword();
//	 public ArrayList<Long> getAllBooksId();
}
