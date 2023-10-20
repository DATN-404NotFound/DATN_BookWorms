/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Account"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="Users", schema="dbo", catalog="BookWorm" )
public class Account implements  UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="Userid", nullable=false, length=10)
    private String     userid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="Username", nullable=false, length=50)
    private String     username ;

    @Column(name="Fullname", nullable=false, length=50)
    private String     fullname ;

    @Column(name="Password", nullable=false, length=50)
    private String     password ;

    @Temporal(TemporalType.DATE)
    @Column(name="Age")
    private Date       age ;

    @Column(name="Email", nullable=false, length=50)
    private String     email ;

    @Column(name="Gender")
    private Boolean    gender ;

    @Column(name="Image", length=40)
    private String     image ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="account", fetch = FetchType.EAGER)
    private List<Authorities> authorities ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Bookings> listOfBookings ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Paymentaccounts> listOfPaymentaccounts ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Shoponlines> listOfShoponlines ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Cart> listOfCart ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Discountcodes> listOfDiscountcodes ; 

    @JsonIgnore
    @OneToMany(mappedBy="account")
    private List<Addressusers> listOfAddressusers ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(userid);
        sb.append("|");
        sb.append(username);
        sb.append("|");
        sb.append(fullname);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(age);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(gender);
        sb.append("|");
        sb.append(image);
        return sb.toString(); 
    }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@PostConstruct
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) authorities;
	}

}
