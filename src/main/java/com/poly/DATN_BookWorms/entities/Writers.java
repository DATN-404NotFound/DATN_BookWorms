/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Writers"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Writers")
public class Writers implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer    writeid ;

    //--- ENTITY DATA FIELDS 
    public Integer    writtingmasterid ;

    public Integer    bookid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="Bookid", referencedColumnName="BookId", insertable=false, updatable=false)
    public Books      books ; 

    @ManyToOne
    @JoinColumn(name="Writtingmasterid", referencedColumnName="WrittingMasterId", insertable=false, updatable=false)
    public Writtingmasters writtingmasters ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(writeid);
        sb.append("|");
        sb.append(writtingmasterid);
        sb.append("|");
        sb.append(bookid);
        return sb.toString(); 
    }

	public Integer getWriteid() {
		return writeid;
	}

	public void setWriteid(Integer writeid) {
		this.writeid = writeid;
	}

	public Integer getWrittingmasterid() {
		return writtingmasterid;
	}

	public void setWrittingmasterid(Integer writtingmasterid) {
		this.writtingmasterid = writtingmasterid;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public Writtingmasters getWrittingmasters() {
		return writtingmasters;
	}

	public void setWrittingmasters(Writtingmasters writtingmasters) {
		this.writtingmasters = writtingmasters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 

}