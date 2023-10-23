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
 * JPA entity class for "Imagebooks"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="ImageBooks", schema="dbo", catalog="BookWorm" )
public class Imagebooks implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="FiledId", nullable=false)
    private Integer    filedid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="Name", length=255)
    private String     name ;

    @Column(name="TypeFile", length=20)
    private String     typefile ;

    @Column(name="BookId")
    private Integer    bookid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="BookId", referencedColumnName="BookId", insertable=false, updatable=false)
    private Books      books ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(filedid);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(typefile);
        sb.append("|");
        sb.append(bookid);
        return sb.toString(); 
    } 

}