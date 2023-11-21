
package com.poly.DATN_BookWorms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * JPA entity class for "Shoponlines"
 *
 * @author Telosys
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Paymentshop")
public class PaymentShop implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentshopid;

    //--- ENTITY DATA FIELDS 
    @Temporal(TemporalType.DATE)
    private Date createat;

    private Boolean status;

    private double valuepayment;

    @ManyToOne
    @JoinColumn(name = "Shopid", referencedColumnName = "Shopid")
    Shoponlines shoponlines;

    //--- toString specific method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(paymentshopid);
        sb.append("|");
        sb.append(createat);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(valuepayment);
        sb.append("|");
        sb.append(shoponlines);
        return sb.toString();
    }

}