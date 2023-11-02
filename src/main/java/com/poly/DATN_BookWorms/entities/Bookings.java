/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;;

/**
 * JPA entity class for "Bookings"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Bookings")
public class Bookings implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    private String     bookingid ;

    //--- ENTITY DATA FIELDS 
    @Temporal(TemporalType.DATE)
    @Column(name="createat")
    private Date       createat ;

    @Column(name="cost")
    private Double     cost ;

    private String     userid ;

    private Integer    orderstatusid ;

    private Integer    shippingunitid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="bookings")
    @JsonIgnore
    private List<Payments> listOfPayments ; 

    @ManyToOne
    @JoinColumn(name="Orderstatusid", referencedColumnName="OrderStatusId", insertable=false, updatable=false)
    private Orderstatuses orderstatuses ; 

    @ManyToOne
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    private Account    account ; 

    @OneToMany(mappedBy="bookings")
    @JsonIgnore
    private List<Detailbookings> listOfDetailbookings ; 

    @ManyToOne
    @JoinColumn(name="Shippingunitid", referencedColumnName="ShippingUnitId", insertable=false, updatable=false)
    private Shippingunits shippingunits ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(bookingid);
        sb.append("|");
        sb.append(createat);
        sb.append("|");
        sb.append(cost);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
        sb.append(orderstatusid);
        sb.append("|");
        sb.append(shippingunitid);
        return sb.toString(); 
    } 

}