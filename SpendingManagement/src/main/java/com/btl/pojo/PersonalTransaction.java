/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trant
 */
@Entity
@Table(name = "personal_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalTransaction.findAll", query = "SELECT p FROM PersonalTransaction p"),
    @NamedQuery(name = "PersonalTransaction.findById", query = "SELECT p FROM PersonalTransaction p WHERE p.id = :id"),
    @NamedQuery(name = "PersonalTransaction.findByName", query = "SELECT p FROM PersonalTransaction p WHERE p.name = :name"),
    @NamedQuery(name = "PersonalTransaction.findByPurpose", query = "SELECT p FROM PersonalTransaction p WHERE p.purpose = :purpose"),
    @NamedQuery(name = "PersonalTransaction.findByDescription", query = "SELECT p FROM PersonalTransaction p WHERE p.description = :description"),
    @NamedQuery(name = "PersonalTransaction.findByPrice", query = "SELECT p FROM PersonalTransaction p WHERE p.price = :price"),
    @NamedQuery(name = "PersonalTransaction.findByDate", query = "SELECT p FROM PersonalTransaction p WHERE p.date = :date")})
public class PersonalTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "purpose")
    private String purpose;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "transaction_type", referencedColumnName = "id")
    @ManyToOne
    private TransactionType transactionType;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public PersonalTransaction() {
    }

    public PersonalTransaction(Integer id) {
        this.id = id;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof PersonalTransaction)) {
            return false;
        }
        PersonalTransaction other = (PersonalTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.btl.pojo.PersonalTransaction[ id=" + id + " ]";
    }
    
}
