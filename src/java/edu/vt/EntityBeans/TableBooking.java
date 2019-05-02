/*
 * Created by Sandhya Manjunatha Bharadwaj on 2019.04.29  * 
 * Copyright © 2019 Sandhya Manjunatha Bharadwaj . All rights reserved. * 
 */

package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "TableBooking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TableBooking.findAll", query = "SELECT t FROM TableBooking t")
    , @NamedQuery(name = "TableBooking.findById", query = "SELECT t FROM TableBooking t WHERE t.id = :id")
    , @NamedQuery(name = "TableBooking.findByUsername", query = "SELECT t FROM TableBooking t WHERE t.username = :username")
    , @NamedQuery(name = "TableBooking.findByFirstName", query = "SELECT t FROM TableBooking t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TableBooking.findByMiddleName", query = "SELECT t FROM TableBooking t WHERE t.middleName = :middleName")
    , @NamedQuery(name = "TableBooking.findByLastName", query = "SELECT t FROM TableBooking t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TableBooking.findByEmail", query = "SELECT t FROM TableBooking t WHERE t.email = :email")
    , @NamedQuery(name = "TableBooking.findByBookingDate", query = "SELECT t FROM TableBooking t WHERE t.bookingDate = :bookingDate")
    , @NamedQuery(name = "TableBooking.findByBookingTime", query = "SELECT t FROM TableBooking t WHERE t.bookingTime = :bookingTime")
    , @NamedQuery(name = "TableBooking.findByNumberPeople", query = "SELECT t FROM TableBooking t WHERE t.numberPeople = :numberPeople")})
public class TableBooking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 32)
    @Column(name = "middle_name")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_date")
    @Temporal(TemporalType.DATE)
    private Date bookingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "booking_time")
    @Temporal(TemporalType.DATE)
    private Date bookingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_people")
    private int numberPeople;

    public TableBooking() {
    }

    public TableBooking(Integer id) {
        this.id = id;
    }

    public TableBooking(Integer id, String username, String firstName, String lastName, String email, Date bookingDate, Date bookingTime, int numberPeople) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.numberPeople = numberPeople;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
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
        if (!(object instanceof TableBooking)) {
            return false;
        }
        TableBooking other = (TableBooking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.TableBooking[ id=" + id + " ]";
    }
    
}
