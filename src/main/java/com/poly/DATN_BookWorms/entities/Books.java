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

    @Column(name = "language", length = 20)
    public String language;

    @Column(name = "size", length = 20)
    public String size;

    @Column(name = "weight")
    public Double weight;

    public Integer totalpage;

    public Integer publishingyear;

    @Column(name = "price")
    public Double price;

    @Column(name = "quantity")
    public Integer quantity;

    @Column(name = "statues", length = 20)
    public String statues;

    public Integer publishingcompanyid;

    public Boolean isactive;
    public Boolean isdelete;


    public Integer quantitysold;

    public Integer shopid;

//    @Column(name= "ProductViews")
//    public Integer productviews;



    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy = "books")
    @JsonIgnore
    public List<Cart> listOfCart;

    @ManyToOne
    @JoinColumn(name = "Shopid", referencedColumnName = "ShopId", insertable = false, updatable = false)
    public Shoponlines shoponlines;

    @OneToMany(mappedBy = "books")
    @JsonIgnore
    public List<Imagebooks> listOfImagebooks;

    @OneToMany(mappedBy = "books")
    @JsonIgnore
    public List<Detailbookings> listOfDetailbookings;

    @OneToMany(mappedBy = "books")
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


    public List<Imagebooks> getListOfImagebooks() {
        // TODO Auto-generated method stub
        return listOfImagebooks;
    }


    public Long getBookid() {
        return bookid;
    }


    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }


    public String getBookname() {
        return bookname;
    }


    public void setBookname(String bookname) {
        this.bookname = bookname;
    }


    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public String getSize() {
        return size;
    }


    public void setSize(String size) {
        this.size = size;
    }


    public Double getWeight() {
        return weight;
    }


    public void setWeight(Double weight) {
        this.weight = weight;
    }


    public Integer getTotalpage() {
        return totalpage;
    }


    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }


    public Integer getPublishingyear() {
        return publishingyear;
    }


    public void setPublishingyear(Integer publishingyear) {
        this.publishingyear = publishingyear;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getStatues() {
        return statues;
    }


    public void setStatues(String statues) {
        this.statues = statues;
    }


    public Integer getPublishingcompanyid() {
        return publishingcompanyid;
    }


    public void setPublishingcompanyid(Integer publishingcompanyid) {
        this.publishingcompanyid = publishingcompanyid;
    }


    public Boolean getIsactive() {
        return isactive;
    }


    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }


    public Integer getQuantitysold() {
        return quantitysold;
    }


    public void setQuantitysold(Integer quantitysold) {
        this.quantitysold = quantitysold;
    }


    public Integer getShopid() {
        return shopid;
    }


    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }


    public List<Cart> getListOfCart() {
        return listOfCart;
    }


    public void setListOfCart(List<Cart> listOfCart) {
        this.listOfCart = listOfCart;
    }


    public Shoponlines getShoponlines() {
        return shoponlines;
    }


    public void setShoponlines(Shoponlines shoponlines) {
        this.shoponlines = shoponlines;
    }


    public List<Detailbookings> getListOfDetailbookings() {
        return listOfDetailbookings;
    }


    public void setListOfDetailbookings(List<Detailbookings> listOfDetailbookings) {
        this.listOfDetailbookings = listOfDetailbookings;
    }


    public List<Typebooks> getListOfTypebooks() {
        return listOfTypebooks;
    }


    public void setListOfTypebooks(List<Typebooks> listOfTypebooks) {
        this.listOfTypebooks = listOfTypebooks;
    }


    public List<Writers> getListOfWriters() {
        return listOfWriters;
    }


    public void setListOfWriters(List<Writers> listOfWriters) {
        this.listOfWriters = listOfWriters;
    }


    public Publishingcompanies getPublishingcompanies() {
        return publishingcompanies;
    }


    public void setPublishingcompanies(Publishingcompanies publishingcompanies) {
        this.publishingcompanies = publishingcompanies;
    }


    public List<Hassales> getListOfHassales() {
        return listOfHassales;
    }


    public void setListOfHassales(List<Hassales> listOfHassales) {
        this.listOfHassales = listOfHassales;
    }


    public void setListOfImagebooks(List<Imagebooks> listOfImagebooks) {
        this.listOfImagebooks = listOfImagebooks;
    }


}