
package com.poly.DATN_BookWorms.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="Account" )
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY
    @Id
    public String     userid ;


    public String     username ;

    public String     fullname ;

    public String     password ;

    @Temporal(TemporalType.DATE)
    @Column(name="Age")
    public Date       age ;

    public String     email ;

    @Column(name="Gender")
    public Boolean    gender ;

    @Column(name="Image", length=40)
    public String     image ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="account", fetch = FetchType.EAGER)
    public List<Authorities> authorities ;

    @JsonIgnore
    @OneToMany(mappedBy="account")
    public List<Bookings> listOfBookings ;

    @JsonIgnore
    @OneToMany(mappedBy="account")
    public List<Paymentaccounts> listOfPaymentaccounts ;

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
    public List<Addressusers> listOfAddressusers ;





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


	
}
