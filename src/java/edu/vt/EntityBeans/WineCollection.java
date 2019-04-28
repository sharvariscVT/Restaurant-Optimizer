/*
 * Created by Sharvari Chougule on 2019.04.23  * 
 * Copyright Â© 2019 Sharvari Chougule. All rights reserved. * 
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "WineCollection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WineCollection.findAll", query = "SELECT w FROM WineCollection w")
    , @NamedQuery(name = "WineCollection.findById", query = "SELECT w FROM WineCollection w WHERE w.id = :id")
    , @NamedQuery(name = "WineCollection.findByWineName", query = "SELECT w FROM WineCollection w WHERE w.wineName = :wineName")
    , @NamedQuery(name = "WineCollection.findByYearofManufacture", query = "SELECT w FROM WineCollection w WHERE w.yearofManufacture = :yearofManufacture")
    , @NamedQuery(name = "WineCollection.findByCountry", query = "SELECT w FROM WineCollection w WHERE w.country = :country")
    , @NamedQuery(name = "WineCollection.findByDescription", query = "SELECT w FROM WineCollection w WHERE w.description = :description")
    , @NamedQuery(name = "WineCollection.findByGlassprice", query = "SELECT w FROM WineCollection w WHERE w.glassprice = :glassprice")
    , @NamedQuery(name = "WineCollection.findByBottleprice", query = "SELECT w FROM WineCollection w WHERE w.bottleprice = :bottleprice")
    , @NamedQuery(name = "WineCollection.findByWinetype", query = "SELECT w FROM WineCollection w WHERE w.winetype = :winetype")})
public class WineCollection implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "WineName")
    private String wineName;
    @Size(max = 4)
    @Column(name = "YearofManufacture")
    private String yearofManufacture;
    @Size(max = 32)
    @Column(name = "Country")
    private String country;
    @Size(max = 500)
    @Column(name = "Description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Glass_price")
    private Float glassprice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Bottle_price")
    private float bottleprice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Wine_type")
    private String winetype;

    public WineCollection() {
    }

    public WineCollection(Integer id) {
        this.id = id;
    }

    public WineCollection(Integer id, String wineName, float bottleprice, String winetype) {
        this.id = id;
        this.wineName = wineName;
        this.bottleprice = bottleprice;
        this.winetype = winetype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getYearofManufacture() {
        return yearofManufacture;
    }

    public void setYearofManufacture(String yearofManufacture) {
        this.yearofManufacture = yearofManufacture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getGlassprice() {
        return glassprice;
    }

    public void setGlassprice(Float glassprice) {
        this.glassprice = glassprice;
    }

    public float getBottleprice() {
        return bottleprice;
    }

    public void setBottleprice(float bottleprice) {
        this.bottleprice = bottleprice;
    }

    public String getWinetype() {
        return winetype;
    }

    public void setWinetype(String winetype) {
        this.winetype = winetype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WineCollection)) {
            return false;
        }
        WineCollection other = (WineCollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.WineCollection[ id=" + id + " ]";
    }
    
}
