
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PublishingCompany implements Serializable {
    private static final long serialVersionUID = 1L;
    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer    pcid ;

    //--- ENTITY DATA FIELDS 
    public String     namepc ;

    public String     phone ;

    public String     emaill ;

    public String     address ;

    public String     profile ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="publishingCompany")
    @JsonIgnore
    private List<Book> listOfBook ;

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