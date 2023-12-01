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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Paymentshop")
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
