
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

/**
 * JPA entity class for "Shoponlines"
 *
 * @author Telosys
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Shoponlines")
public class Shoponlines implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopid;

    //--- ENTITY DATA FIELDS 
    private String shopname;

    private String phonenumber;


    @Column(name = "total")
    private Double total;


    @Column(name = "description", length = 2555)
    private String description;

    private String     userid ;

    @Column(name = "paycount", length = 10)
    public String paycount;

    public Boolean isactive;
    


    //--- ENTITY LINKS ( RELATIONSHIP )

    @OneToMany(mappedBy = "shoponlines")
    @JsonIgnore
    public List<Books> listOfBooks;

    @OneToMany(mappedBy = "shoponlines")
    @JsonIgnore
    public List<Files> listOfFiles ; 
    
    @OneToMany(mappedBy = "shoponlines")
    @JsonIgnore
    private List<AddressShop> listOfAddressShop;

    @OneToMany(mappedBy = "shoponlines")
    @JsonIgnore
    private List<PaymentShop> listOfPaymentShop;

    
    @ManyToOne
    @JoinColumn(name="Userid", referencedColumnName="Userid", insertable=false, updatable=false)
    public Account    account ; 

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
        sb.append(total);
        sb.append("|");
        sb.append(description);
        sb.append("|");

        sb.append(listOfAddressShop);

        sb.append(userid);
        sb.append("|");
        sb.append(paycount);
        sb.append("|");
        sb.append(isactive);
        return sb.toString();
    }

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPaycount() {
		return paycount;
	}

	public void setPaycount(String paycount) {
		this.paycount = paycount;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public List<Books> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Books> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	public List<Files> getListOfFiles() {
		return listOfFiles;
	}

	public void setListOfFiles(List<Files> listOfFiles) {
		this.listOfFiles = listOfFiles;
	}

	public List<AddressShop> getListOfAddressShop() {
		return listOfAddressShop;
	}

	public void setListOfAddressShop(List<AddressShop> listOfAddressShop) {
		this.listOfAddressShop = listOfAddressShop;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}