/*
 * Created by Sharvari Chougule on 2019.04.29  * 
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
@Table(name = "FoodMenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FoodMenu.findAll", query = "SELECT f FROM FoodMenu f")
    , @NamedQuery(name = "FoodMenu.findById", query = "SELECT f FROM FoodMenu f WHERE f.id = :id")
    , @NamedQuery(name = "FoodMenu.findByName", query = "SELECT f FROM FoodMenu f WHERE f.name = :name")
    , @NamedQuery(name = "FoodMenu.findByDescription", query = "SELECT f FROM FoodMenu f WHERE f.description = :description")
    , @NamedQuery(name = "FoodMenu.findByFoodType", query = "SELECT f FROM FoodMenu f WHERE f.foodType = :foodType")
    , @NamedQuery(name = "FoodMenu.findByPrice", query = "SELECT f FROM FoodMenu f WHERE f.price = :price")})
public class FoodMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 125)
    @Column(name = "Name")
    private String name;
    @Size(max = 300)
    @Column(name = "Description")
    private String description;
    @Size(max = 64)
    @Column(name = "FoodType")
    private String foodType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private float price;

    public FoodMenu() {
    }

    public FoodMenu(Integer id) {
        this.id = id;
    }

    public FoodMenu(Integer id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        if (!(object instanceof FoodMenu)) {
            return false;
        }
        FoodMenu other = (FoodMenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.FoodMenu[ id=" + id + " ]";
    }
    
}
