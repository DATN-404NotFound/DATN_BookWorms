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
@Table(name="Bookings", schema="dbo", catalog="BookWorm" )
public class Bookings implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="BookingId", nullable=false, length=10)
    private String     bookingid ;

    //--- ENTITY DATA FIELDS 
    @Temporal(TemporalType.DATE)
    @Column(name="CreateAt")
    private Date       createat ;

    @Column(name="Cost")
    private Double     cost ;

    @Column(name="UserId", nullable=false, length=10)
    private String     userid ;

    @Column(name="OrderStatusId", nullable=false)
    private Integer    orderstatusid ;

    @Column(name="ShippingUnitId", nullable=false)
    private Integer    shippingunitid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="bookings")
    private List<Payments> listOfPayments ; 

    @ManyToOne
    @JoinColumn(name="OrderStatusId", referencedColumnName="OrderStatusId", insertable=false, updatable=false)
    private Orderstatuses orderstatuses ; 

    @ManyToOne
    @JoinColumn(name="UserId", referencedColumnName="Userid", insertable=false, updatable=false)
    private Account    account ; 

    @OneToMany(mappedBy="bookings")
    private List<Detailbookings> listOfDetailbookings ; 

    @ManyToOne
    @JoinColumn(name="ShippingUnitId", referencedColumnName="ShippingUnitId", insertable=false, updatable=false)
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
