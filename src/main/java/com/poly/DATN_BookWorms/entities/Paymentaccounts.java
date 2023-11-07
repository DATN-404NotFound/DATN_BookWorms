/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA entity class for "Paymentaccounts"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Paymentaccounts")
public class Paymentaccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    public String     paid ;

    //--- ENTITY DATA FIELDS 
    public String     userid ;

    @Column(name="name", length=30)
    public String     name ;

    public String     cccd ;

    public String     bankname ;

    public String     accountnumber ;

    public String     accountname ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="paymentaccounts")
    @JsonIgnore
    public List<Payments> listOfPayments ; 

    @ManyToOne
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    private Account    account ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(paid);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
        sb.append(name);
        sb.append("|");
        sb.append(cccd);
        sb.append("|");
        sb.append(bankname);
        sb.append("|");
        sb.append(accountnumber);
        sb.append("|");
        sb.append(accountname);
        return sb.toString(); 
    } 

}