
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Payments"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Payments")
public class Payments implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    private String     paymentid ;

    //--- ENTITY DATA FIELDS 
    private String     bookingid ;

    @Temporal(TemporalType.DATE)
    private Date       createat ;

    @Column(name="status", nullable=false, length=20)
    private String     status ;

    private String     paid ;

    @Column(name="type")
    private Boolean    type ;

    private String     addressuserid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="Addressuserid", referencedColumnName="AddressUserId", insertable=false, updatable=false)
    private Addressusers addressusers ; 

    @ManyToOne
    @JoinColumn(name="paid", referencedColumnName="PAId", insertable=false, updatable=false)
    private Paymentaccounts paymentaccounts ; 

    @ManyToOne
    @JoinColumn(name="Bookingid", referencedColumnName="BookingId", insertable=false, updatable=false)
    private Bookings   bookings ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(paymentid);
        sb.append("|");
        sb.append(bookingid);
        sb.append("|");
        sb.append(createat);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(paid);
        sb.append("|");
        sb.append(type);
        sb.append("|");
        sb.append(addressuserid);
        return sb.toString(); 
    } 

}