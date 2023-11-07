/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Files"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Files")
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer    fileid ;

    //--- ENTITY DATA FIELDS 
    public String     filename ;

    public String     typefile ;

    public Integer    shopid ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="Shopid", referencedColumnName="ShopId", insertable=false, updatable=false)
    public Shoponlines shoponlines ; 
    
    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(fileid);
        sb.append("|");
        sb.append(filename);
        sb.append("|");
        sb.append(typefile);
        sb.append("|");
        sb.append(shopid);
        return sb.toString(); 
    } 

}