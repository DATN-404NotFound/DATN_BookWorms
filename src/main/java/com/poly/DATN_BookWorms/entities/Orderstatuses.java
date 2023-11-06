/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * JPA entity class for "Orderstatuses"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Orderstatuses")
public class Orderstatuses implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer    orderstatusid ;

    //--- ENTITY DATA FIELDS 
    private String     statusname ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="orderstatuses")
    @JsonIgnore
    private List<Bookings> listOfBookings ; 
    
    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(orderstatusid);
        sb.append("|");
        sb.append(statusname);
        return sb.toString(); 
    }

	public Integer getOrderstatusid() {
		return orderstatusid;
	}

	public void setOrderstatusid(Integer orderstatusid) {
		this.orderstatusid = orderstatusid;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public List<Bookings> getListOfBookings() {
		return listOfBookings;
	}

	public void setListOfBookings(List<Bookings> listOfBookings) {
		this.listOfBookings = listOfBookings;
	} 

}