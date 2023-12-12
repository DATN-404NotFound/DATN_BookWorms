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

    private String detailhome;

	private  String ward;

	private  String district;

	private  String  province;


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
        sb.append(detailhome);
        sb.append("|");
		sb.append(ward);
		sb.append("|");
		sb.append(district);
		sb.append("|");
		sb.append(province);
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

}
