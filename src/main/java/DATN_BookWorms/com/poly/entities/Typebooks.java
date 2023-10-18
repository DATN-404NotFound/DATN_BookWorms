/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Typebooks"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="TypeBooks", schema="dbo", catalog="BookWorm" )
public class Typebooks implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="TypeBookId", nullable=false)
    private Integer    typebookid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="CategoryId")
    private Integer    categoryid ;

    @Column(name="BookId")
    private Integer    bookid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="BookId", referencedColumnName="BookId", insertable=false, updatable=false)
    private Books      books ; 

    @ManyToOne
    @JoinColumn(name="CategoryId", referencedColumnName="CategoryId", insertable=false, updatable=false)
    private Categories categories ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(typebookid);
        sb.append("|");
        sb.append(categoryid);
        sb.append("|");
        sb.append(bookid);
        return sb.toString(); 
    } 

}
