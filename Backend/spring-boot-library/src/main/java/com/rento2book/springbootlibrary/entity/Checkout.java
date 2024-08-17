package com.rento2book.springbootlibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "checkout")
public class Checkout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "checkout_date")
	private String checkoutdate;
	
	@Column(name = "return_date")
	private String returndate;
	
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book bookid;
	
	
	public Checkout() {
		
	}

	public Checkout(long id, String email, String checkoutdate, String returndate, Book bookid) {
		super();
		this.id = id;
		this.email = email;
		this.checkoutdate = checkoutdate;
		this.returndate = returndate;
		this.bookid = bookid;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCheckoutdate() {
		return checkoutdate;
	}


	public void setCheckoutdate(String checkoutdate) {
		this.checkoutdate = checkoutdate;
	}


	public String getReturndate() {
		return returndate;
	}


	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}


	public Book getBookid() {
		return bookid;
	}


	public void setBookid(Book bookid) {
		this.bookid = bookid;
	}
	
	

}
