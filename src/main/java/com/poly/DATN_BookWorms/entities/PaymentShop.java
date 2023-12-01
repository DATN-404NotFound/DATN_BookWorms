<<<<<<< HEAD

package com.poly.DATN_BookWorms.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
=======
package com.poly.DATN_BookWorms.entities;

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
>>>>>>> zendyy/back_end
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * JPA entity class for "Shoponlines"
 *
 * @author Telosys
 */
=======
>>>>>>> zendyy/back_end
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Paymentshop")
<<<<<<< HEAD
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
=======
public class PaymentShop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paymentshopid", nullable = false)
	private int paymentshopid;
	
	@Temporal(TemporalType.DATE)
    private Date       createat ;
	
	private int status;
	
	private long valuepayment;
	
	@ManyToOne
    @JoinColumn(name="Shopid", referencedColumnName="ShopId", insertable=false, updatable=false)
    private Shoponlines shoponlines ; 
}
>>>>>>> zendyy/back_end
