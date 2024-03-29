/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="cart")
public class Cart implements Serializable {
    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cartid")
    public Long cartid ;

    //--- ENTITY DATA FIELDS 
    public String userid ;

    public Integer bookid ;

    @Column(name="quantity")
    public Integer quantity ;

    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne 
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    public Account account ;

    @ManyToOne
    @JoinColumn(name="Bookid", referencedColumnName="BookId", insertable=false, updatable=false)
    public Book book ;

	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(cartid);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
        sb.append(bookid);
        sb.append("|");
        sb.append(quantity);
        return sb.toString(); 
    }
}