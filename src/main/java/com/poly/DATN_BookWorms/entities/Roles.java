/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * JPA entity class for "Roles"
 *
 * @author Telosys
 *
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Roles")
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    private String     roleid ;

    //--- ENTITY DATA FIELDS 
    private String     rolename ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="roles")
    @JsonIgnore
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

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Authorities> getListOfAuthorities() {
		return listOfAuthorities;
	}

	public void setListOfAuthorities(List<Authorities> listOfAuthorities) {
		this.listOfAuthorities = listOfAuthorities;
	}
//
//	public Roles(String roleid, String rolename, List<Authorities> listOfAuthorities) {
//		super();
//		this.roleid = roleid;
//		this.rolename = rolename;
//		this.listOfAuthorities = listOfAuthorities;
//	}
//
//	public Roles() {
//		super();
//	}


}