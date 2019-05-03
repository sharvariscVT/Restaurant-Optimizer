/*
 * Created by Ashish Kotian on 2019.05.02
 * Copyright © 2019 Ashish Kotian. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ashishkotian
 */
@Entity
@Table(name = "suggestions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suggestions.findAll", query = "SELECT s FROM Suggestions s")
    , @NamedQuery(name = "Suggestions.findBySuggestion", query = "SELECT s FROM Suggestions s WHERE s.suggestion = :suggestion")})
public class Suggestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "suggestion")
    private String suggestion;

    public Suggestions() {
    }

    public Suggestions(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSuggestion() {
        System.out.println("Geeeeetttttselected");
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suggestion != null ? suggestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suggestions)) {
            return false;
        }
        Suggestions other = (Suggestions) object;
        if ((this.suggestion == null && other.suggestion != null) || (this.suggestion != null && !this.suggestion.equals(other.suggestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.Suggestions[ suggestion=" + suggestion + " ]";
    }
    
}
