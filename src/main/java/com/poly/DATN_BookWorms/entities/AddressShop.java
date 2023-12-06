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

    private Boolean isdelete;

    @ManyToOne
    @JoinColumn(name = "Shopid", referencedColumnName="ShopId")
    private Shoponlines shoponlines ;


}
