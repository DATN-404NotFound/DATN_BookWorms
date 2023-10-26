/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
 * JPA entity class for "Discountcodes"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Discountcodes" )
public class Discountcodes implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer    discountcodeid ;

    //--- ENTITY DATA FIELDS 
    private String     saleid ;

    private String     userid ;

    @Temporal(TemporalType.DATE)
    private Date       startdiscount ;

    @Temporal(TemporalType.DATE)
    private Date       enddiscount ;

    private Boolean    isdelete ;

    private Double     minprice ;

    @Column(name="Status", length=50)
    private String     status ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    private Account    account ; 

    @ManyToOne
    @JoinColumn(name="Saleid", referencedColumnName="CouoponCode", insertable=false, updatable=false)
    private Sales      sales ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(discountcodeid);
        sb.append("|");
        sb.append(saleid);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
        sb.append(startdiscount);
        sb.append("|");
        sb.append(enddiscount);
        sb.append("|");
        sb.append(isdelete);
        sb.append("|");
        sb.append(minprice);
        sb.append("|");
        sb.append(status);
        return sb.toString(); 
    } 

}
