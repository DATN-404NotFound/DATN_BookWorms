/*
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.poly.DATN_BookWorms.entities;

import java.io.Serializable;
import java.util.Date;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA entity class for "Evaluates"
 *
 * @author Telosys
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Evaluates")
public class Evaluates implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer    evaluateid ;

    //--- ENTITY DATA FIELDS 
    private String     dbid ;

    @Column(name="Rating")
    private Integer    rating ;

    private String     reviewtext ;

    @Temporal(TemporalType.DATE)
    private Date       evaluatedate ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="dbid", referencedColumnName="DBId", insertable=false, updatable=false)
    private Detailbookings detailbookings ; 

    @OneToMany(mappedBy="evaluates")
    private List<Imageevaluates> listOfImageevaluates ; 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(evaluateid);
        sb.append("|");
        sb.append(dbid);
        sb.append("|");
        sb.append(rating);
        sb.append("|");
        sb.append(reviewtext);
        sb.append("|");
        sb.append(evaluatedate);
        return sb.toString(); 
    } 

}
