/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Imageevaluates"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="imageevaluates", schema="dbo", catalog="bookworm" )
public class Imageevaluates implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="imageevaId", nullable=false)
    private Integer    imageevaid ;

    //--- ENTITY DATA FIELDS 
    @Column(name="image", length=50)
    private String     image ;

    @Column(name="evaluateId")
    private Integer    evaluateid ;

    @Column(name="type", length=20)
    private String     type ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="EvaluateId", referencedColumnName="EvaluateId", insertable=false, updatable=false)
    private Evaluates  evaluates ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(imageevaid);
        sb.append("|");
        sb.append(image);
        sb.append("|");
        sb.append(evaluateid);
        sb.append("|");
        sb.append(type);
        return sb.toString(); 
    } 

}
