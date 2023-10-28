
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

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
 * JPA entity class for "Publishingcompanies"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="publishingcompanies")
public class Publishingcompanies implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer    pcid ;

    //--- ENTITY DATA FIELDS 
    private String     namepc ;

    private String     phone ;

    private String     emaill ;

    private String     address ;

    private String     profile ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="publishingcompanies")
    private List<Books> listOfBooks ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(pcid);
        sb.append("|");
        sb.append(namepc);
        sb.append("|");
        sb.append(phone);
        sb.append("|");
        sb.append(emaill);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(profile);
        return sb.toString(); 
    } 

}