
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.List;

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
@Table(name="shoponlines", schema="dbo", catalog="bookworm" )
public class Shoponlines implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="shopid", nullable=false)
    private Integer    shopid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="shopname", length=50)
    private String     shopname ;

    @Column(name="phonenumber", length=11)
    private String     phonenumber ;

    @Column(name="logo", length=50)
    private String     logo ;

    @Column(name="total")
    private Double     total ;

    @Column(name="banner", length=50)
    private String     banner ;

    @Column(name="description", length=2555)
    private String     description ;

    @Column(name="shopaddress", length=100)
    private String     shopaddress ;


    @Column(name="paycount", length=10)
    private String     paycount ;

    @Column(name="isactive")
    private Boolean    isactive ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @OneToMany(mappedBy="shoponlines")
    private List<Books> listOfBooks ; 

    @OneToMany(mappedBy="shoponlines")
    private List<Files> listOfFiles ; 

    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName="userid", insertable=false, updatable=false)
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
