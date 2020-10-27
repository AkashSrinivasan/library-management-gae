package com.management;

import java.time.LocalDateTime;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Book {
	@Id
	Long id;
	String name;
	Long barrowerId;
	String borrowedOn;
	String dueDate;
	
	Book(){}
	
	Book(long i,String name,long j,String borrowedOn,String dueDate ){
		this.id = i;
		this.name = name;
		this.barrowerId = j;
		this.borrowedOn = borrowedOn;
		this.dueDate = dueDate;
	}
	
	public LocalDateTime getBorrowedOn() {
		return LocalDateTime.parse(this.borrowedOn);
	}
	
	public LocalDateTime getDueDate() {
		return LocalDateTime.parse(this.dueDate);
	}
}
