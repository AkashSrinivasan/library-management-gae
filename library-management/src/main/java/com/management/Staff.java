package com.management;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Staff implements User{
	@Id
	long id;
	String name;
	String password;
//	ArrayList<Long> allBookId = new ArrayList<Long>();
	
	Staff(int id, String name, String password){
		this.id = id;
		this.name = name;
		this.password = password;
	}
	Staff(){}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}

	@Override
	public void setPassword(String id) {
		// TODO Auto-generated method stub
		this.password = name;
	}
//
//	@Override
//	public void setBook(Long bookId) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

//	@Override
//	public ArrayList<Long> getAllBooksId() {
//		// TODO Auto-generated method stub
//		return this.allBookId;
//	}
}
