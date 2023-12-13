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
 * JPA entity class for "Addressusers"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Addressusers")
public class Addressusers implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    public String     addressuserid ;

    //--- ENTITY DATA FIELDS 
    private String     userid ;


    public String     detailhome ;

	public String     Ward ;

	public String     district ;

	public String    province ;

    @Column(name="fullname", nullable=false, length=50)
    public String     fullname ;

    public String     phonenumber ;

    public String     statusaddress ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
   
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    public Account    account ; 

    @OneToMany(mappedBy="addressusers")
    @JsonIgnore
    public List<Payments> listOfPayments ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(addressuserid);
        sb.append("|");
        sb.append(userid);
        sb.append("|");
		sb.append(detailhome);
        sb.append("|");
		sb.append(Ward);
        sb.append("|");
        sb.append(district);
        sb.append("|");
		sb.append(province);
        sb.append("|");
        sb.append(fullname);
        sb.append("|");
        sb.append(phonenumber);
        sb.append("|");
        sb.append(statusaddress);
        return sb.toString(); 
    }

}