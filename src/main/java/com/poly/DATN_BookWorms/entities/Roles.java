/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Roles"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="roles", schema="dbo", catalog="bookworm" )
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="roleid", nullable=false, length=10)
    private String     roleid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="rolename", nullable=false, length=20)
    private String     rolename ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="roles")
    private List<Authorities> listOfAuthorities ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(roleid);
        sb.append("|");
        sb.append(rolename);
        return sb.toString(); 
    }

	public Roles(String rolename) {
		this.rolename = rolename;
	} 

}
