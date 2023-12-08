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
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long bookid;

    //--- ENTITY DATA FIELDS 
    public String bookname;

    public String language;


    public String size;


    public Double weight;

    public Integer totalpage;

    public Integer publishingyear;


    public Double price;


    public Integer quantity;

    public String statues;

    public Integer publishingcompanyid;

    public Boolean isactive;
    public Boolean isdelete;


    public Integer quantitysold;

    public Integer shopid;


    public Integer productviews;



    //--- ENTITY LINKS ( RELATIONSHIP )

    @OneToMany(mappedBy="books", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Cart> listOfCart ; 


    @ManyToOne
    @JoinColumn(name = "Shopid", referencedColumnName = "ShopId", insertable = false, updatable = false)
    public Shoponlines shoponlines;


    @OneToMany(mappedBy="books" , fetch = FetchType.EAGER)
    @JsonIgnore
    public List<Imagebooks> listOfImagebooks;


    @OneToMany(mappedBy="books" , fetch = FetchType.EAGER)
    @JsonIgnore
    public List<Detailbookings> listOfDetailbookings;


    @OneToMany(mappedBy="books" , fetch = FetchType.EAGER)

    @JsonIgnore
    public List<Typebooks> listOfTypebooks;

    @OneToMany(mappedBy = "books")
    @JsonIgnore
    public List<Writers> listOfWriters;

    @ManyToOne
    @JoinColumn(name = "Publishingcompanyid", referencedColumnName = "PCId", insertable = false, updatable = false)
    public Publishingcompanies publishingcompanies;

    @OneToMany(mappedBy = "books")
    @JsonIgnore
    public List<Hassales> listOfHassales;



  //--- toString specific method

    //--- toString specific method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.bookid);
        sb.append("|");
        sb.append(this.statues);
        sb.append("|");
        sb.append(this.size);
        sb.append("|");
        sb.append(this.bookname);
        sb.append("|");
        sb.append(this.productviews);
        sb.append("|");
        sb.append(this.publishingcompanyid);
        sb.append("|");
        sb.append(this.quantitysold);
        sb.append("|");
        sb.append(this.shopid);
        sb.append("|");
        sb.append(this.isactive);
        sb.append("|");
        sb.append(this.isdelete);
        sb.append("|");
        sb.append(this.quantity);
        sb.append("|");
        sb.append(this.language);
        sb.append("|");
        sb.append(this.price);
        sb.append("|");
        sb.append(this.totalpage);
        sb.append("|");
        sb.append(this.publishingyear);
        sb.append("|");
        sb.append(this.weight);
        sb.append("|");
        return sb.toString();
    }

}