/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name="Books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long    bookid ;

    //--- ENTITY DATA FIELDS 
    public String     bookname ;

    @Column(name="language", length=20)
    public String     language ;

    @Column(name="size", length=20)
    public String     size ;

    @Column(name="weight")
    public Double     weight ;

    public Integer    totalpage ;

    public Integer    publishingyear ;

    @Column(name="price")
    public Double     price ;

    @Column(name="quantity")
    public Integer    quantity ;

    @Column(name="statues", length=20)
    public String     statues ;

    public Integer    publishingcompanyid ;

    public Boolean    isactive ;

    public Integer    quantitysold ;

    public Integer    shopid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="books")
    @JsonIgnore
    public List<Cart> listOfCart ; 

    @ManyToOne
    @JoinColumn(name="Shopid", referencedColumnName="ShopId", insertable=false, updatable=false)
    public Shoponlines shoponlines ; 

    @OneToMany(mappedBy="books")
    @JsonIgnore
    public List<Imagebooks> listOfImagebooks ; 

    @OneToMany(mappedBy="books")
    @JsonIgnore
    public List<Detailbookings> listOfDetailbookings ; 

    @OneToMany(mappedBy="books")
    @JsonIgnore
    public List<Typebooks> listOfTypebooks ; 

    @OneToMany(mappedBy="books")
    @JsonIgnore
    private List<Writers> listOfWriters ; 

    @ManyToOne 

    @JoinColumn(name="Publishingcompanyid", referencedColumnName="PCId", insertable=false, updatable=false)
    private Publishingcompanies publishingcompanies ; 

    @OneToMany(mappedBy="books")
    @JsonIgnore
    public List<Hassales> listOfHassales ; 


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