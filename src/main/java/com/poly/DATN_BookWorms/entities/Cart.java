/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;

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
 * JPA entity class for "Cart"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="cart", schema="dbo", catalog="bookworm" )
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="cartid", nullable=false)
    private Integer    cartid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="userid", length=10)
    private String     userid ;

    @Column(name="bookid")
    private Integer    bookid ;

    @Column(name="quantity")
    private Integer    quantity ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName="userid", insertable=false, updatable=false)
    private Account    account ; 

    @ManyToOne
    @JoinColumn(name="bookid", referencedColumnName="bookid", insertable=false, updatable=false)
    private Books      books ; 

    //--- toString specific method
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
