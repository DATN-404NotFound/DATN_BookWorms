package com.poly.DATN_BookWorms.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Addressshop")
public class AddressShop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressshopid;

    private String namereceiver;

    private String address;

    @Temporal(TemporalType.DATE)
    @Column(name="createat")
    private Date createat ;

    private Boolean isactive;

    private String phonenumber;

    @ManyToOne
    @JoinColumn(name = "Shopid", referencedColumnName="ShopId")
    private Shoponlines shoponlines ;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(addressshopid);
        sb.append("|");
        sb.append(namereceiver);
        sb.append("|");
        sb.append(address);
        sb.append("|");
        sb.append(phonenumber);
        sb.append("|");
        sb.append(createat);
        sb.append("|");
        sb.append(isactive);
        sb.append("|");
        sb.append(shoponlines);
        return sb.toString();
    }

	public Integer getAddressshopid() {
		return addressshopid;
	}

	public void setAddressshopid(Integer addressshopid) {
		this.addressshopid = addressshopid;
	}

	public String getNamereceiver() {
		return namereceiver;
	}

	public void setNamereceiver(String namereceiver) {
		this.namereceiver = namereceiver;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateat() {
		return createat;
	}

	public void setCreateat(Date createat) {
		this.createat = createat;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Shoponlines getShoponlines() {
		return shoponlines;
	}

	public void setShoponlines(Shoponlines shoponlines) {
		this.shoponlines = shoponlines;
	}


<<<<<<< HEAD
}
=======
}
>>>>>>> zendyy/back_end
