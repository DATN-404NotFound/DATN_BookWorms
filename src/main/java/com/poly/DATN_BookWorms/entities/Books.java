/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Books"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="books", schema="dbo", catalog="bookworm" )
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="bookid", nullable=false)
    private Integer    bookid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="bookname", nullable=false, length=100)
    private String     bookname ;

    @Column(name="language", length=20)
    private String     language ;

    @Column(name="size", length=20)
    private String     size ;

    @Column(name="weight")
    private Double     weight ;

    @Column(name="totalpage")
    private Integer    totalpage ;

    @Column(name="publishingyear")
    private Integer    publishingyear ;

    @Column(name="price")
    private Double     price ;

    @Column(name="quantity")
    private Integer    quantity ;

    @Column(name="statues", length=20)
    private String     statues ;

    @Column(name="publishingcompanyid", nullable=false)
    private Integer    publishingcompanyid ;

    @Column(name="isactive")
    private Boolean    isactive ;

    @Column(name="quantitysold")
    private Integer    quantitysold ;

    @Column(name="shopid", nullable=false)
    private Integer    shopid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="books")
    private List<Cart> listOfCart ;

    @ManyToOne
    @JoinColumn(name="shopid", referencedColumnName="shopid", insertable=false, updatable=false)
    private Shoponlines shoponlines ; 

    @OneToMany(mappedBy="books")
    private List<Imagebooks> listOfImagebooks ; 

    @OneToMany(mappedBy="books")
    private List<Detailbookings> listOfDetailbookings ; 

    @OneToMany(mappedBy="books")
    private List<Typebooks> listOfTypebooks ; 

    @OneToMany(mappedBy="books")
    private List<Writers> listOfWriters ;

    @ManyToOne
    @JoinColumn(name="publishingcompanyid", referencedColumnName="pcid", insertable=false, updatable=false)
    private Publishingcompanies publishingcompanies ;

    @OneToMany(mappedBy="books")
    private List<Hassales> listofhassales ;


    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(bookid);
        sb.append("|");
        sb.append(bookname);
        sb.append("|");
        sb.append(language);
        sb.append("|");
        sb.append(size);
        sb.append("|");
        sb.append(weight);
        sb.append("|");
        sb.append(totalpage);
        sb.append("|");
        sb.append(publishingyear);
        sb.append("|");
        sb.append(price);
        sb.append("|");
        sb.append(quantity);
        sb.append("|");
        sb.append(statues);
        sb.append("|");
        sb.append(publishingcompanyid);
        sb.append("|");
        sb.append(isactive);
        sb.append("|");
        sb.append(quantitysold);
        sb.append("|");
        sb.append(shopid);
        return sb.toString(); 
    } 

}
