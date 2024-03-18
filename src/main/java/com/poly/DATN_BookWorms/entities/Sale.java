/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Sales"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Sales")
public class Sale implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    private String     couoponcode ;

    //--- ENTITY DATA FIELDS 
    public String     promotionname ;

    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
    public Date       createat ;
    
    @Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
    public Date       enddiscount ;

    @Column(name="descriptions", length=255)
    public String     descriptions ;

    public BigDecimal discountpercentage ;

    @Column(name="statuses", length=20)
    public String     statuses ;

    public String     intendfor ;

    public Integer    shopid ;
    
    public Double     minprice ;

    public boolean isdelete;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="sale")
    @JsonIgnore
    private List<DiscountCode> listOfDiscountCode ;


    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(couoponcode);
        sb.append("|");
        sb.append(promotionname);
        sb.append("|");
        sb.append(createat);
        sb.append("|");
        sb.append(descriptions);
        sb.append("|");
        sb.append(discountpercentage);
        sb.append("|");
        sb.append(statuses);
        sb.append("|");
        sb.append(intendfor);
        sb.append("|");
        sb.append(shopid);
        return sb.toString(); 
    }
}