/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA entity class for "Detailbookings"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Detailbookings")
public class Detailbookings implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    public String     dbid ;

    //--- ENTITY DATA FIELDS 
    public Long    bookid ;

    public String     bookingid ;

    @Column(name="quantity", nullable=false)
    public Integer    quantity ;
    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="Bookingid", referencedColumnName="BookingId", insertable=false, updatable=false)
    public Bookings   bookings ; 

    @OneToMany(mappedBy="detailbookings")
    @JsonIgnore
    public List<Evaluates> listOfEvaluates ; 

    @ManyToOne
    @JoinColumn(name="Bookid", referencedColumnName="BookId", insertable=false, updatable=false)
    public Books      books ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(dbid);
        sb.append("|");
        sb.append(bookid);
        sb.append("|");
        sb.append(bookingid);
        sb.append("|");
        sb.append(quantity);
        return sb.toString(); 
    }

	public Bookings setBookings(Bookings booking) {
		// TODO Auto-generated method stub
		return booking;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public Long getBookid() {
		return bookid;
	}

	public void setBookid(Long bookid) {
		this.bookid = bookid;
	}

	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Evaluates> getListOfEvaluates() {
		return listOfEvaluates;
	}

	public void setListOfEvaluates(List<Evaluates> listOfEvaluates) {
		this.listOfEvaluates = listOfEvaluates;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Bookings getBookings() {
		return bookings;
	} 

}