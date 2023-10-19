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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Shippingunits"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="ShippingUnits", schema="dbo", catalog="BookWorm" )
public class Shippingunits implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="ShippingUnitId", nullable=false)
    private Integer    shippingunitid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="Name", length=50)
    private String     name ;

    @Column(name="PhoneNumber", length=11)
    private String     phonenumber ;

    @Column(name="Email", length=50)
    private String     email ;

    @Column(name="Address", length=100)
    private String     address ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="shippingunits")
    private List<Bookings> listOfBookings ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(shippingunitid);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(phonenumber);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(address);
        return sb.toString(); 
    } 

}
