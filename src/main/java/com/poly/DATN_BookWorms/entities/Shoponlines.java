
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
 * JPA entity class for "Shoponlines"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Shoponlines")
public class Shoponlines implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer    shopid ;

    //--- ENTITY DATA FIELDS 
    public String     shopname ;

    public String     phonenumber ;

    @Column(name="logo", length=50)
    public  String     logo ;

    @Column(name="total")
    public Double     total ;

    @Column(name="banner", length=50)
    public String     banner ;

    @Column(name="description", length=2555)
    public String     description ;

    private String     shopaddress ;

    private String     userid ;

    @Column(name="paycount", length=10)
    private String     paycount ;

    private Boolean    isactive ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="shoponlines")
    @JsonIgnore
    private List<Books> listOfBooks ; 

    @OneToMany(mappedBy="shoponlines")
    @JsonIgnore
    private List<Files> listOfFiles ; 

    @ManyToOne
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    private Account    account ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(shopid);
        sb.append("|");
        sb.append(shopname);
        sb.append("|");
        sb.append(phonenumber);
        sb.append("|");
        sb.append(logo);
        sb.append("|");
        sb.append(total);
        sb.append("|");
        sb.append(banner);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(shopaddress);
        sb.append("|");
        sb.append(paycount);
        sb.append("|");
        sb.append(isactive);
        return sb.toString(); 
    } 

}