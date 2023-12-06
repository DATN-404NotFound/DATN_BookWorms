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

import java.io.Serializable;

/**
 * JPA entity class for "Shoponlines"
 *
 * @author Telosys
 */


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Paymentshop")
public class PaymentShop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paymentshopid", nullable = false)
	public Long paymentshopid;
	
	@Temporal(TemporalType.DATE)
	public Date       createat ;
	
	public boolean status;
	
	public long valuepayment;
	
	@ManyToOne
    @JoinColumn(name="Shopid", referencedColumnName="ShopId", insertable=false, updatable=false)
	public Shoponlines shoponlines ;
	
	public boolean isdelete;

	public Long getPaymentshopid() {
		return paymentshopid;
	}

	public void setPaymentshopid(Long paymentshopid) {
		this.paymentshopid = paymentshopid;
	}

	public Date getCreateat() {
		return createat;
	}

	public void setCreateat(Date createat) {
		this.createat = createat;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getValuepayment() {
		return valuepayment;
	}

	public void setValuepayment(long valuepayment) {
		this.valuepayment = valuepayment;
	}

	public Shoponlines getShoponlines() {
		return shoponlines;
	}

	public void setShoponlines(Shoponlines shoponlines) {
		this.shoponlines = shoponlines;
	}

	public boolean isIsdelete() {
		return isdelete;
	}

	public void setIsdelete(boolean isdelete) {
		this.isdelete = isdelete;
	}




	

}

