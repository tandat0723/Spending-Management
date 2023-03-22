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
@Table(name = "feature_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeatureDetail.findAll", query = "SELECT f FROM FeatureDetail f"),
    @NamedQuery(name = "FeatureDetail.findById", query = "SELECT f FROM FeatureDetail f WHERE f.id = :id"),
    @NamedQuery(name = "FeatureDetail.findByAmount", query = "SELECT f FROM FeatureDetail f WHERE f.amount = :amount"),
    @NamedQuery(name = "FeatureDetail.findByNum", query = "SELECT f FROM FeatureDetail f WHERE f.num = :num"),
    @NamedQuery(name = "FeatureDetail.findByCreatedDate", query = "SELECT f FROM FeatureDetail f WHERE f.createdDate = :createdDate")})
public class FeatureDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private Long amount;
    @Size(max = 45)
    @Column(name = "num")
    private String num;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Subcategory subcategoryId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public FeatureDetail() {
    }

    public FeatureDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Subcategory getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(Subcategory subcategoryId) {
        this.subcategoryId = subcategoryId;
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
        if (!(object instanceof FeatureDetail)) {
            return false;
        }
        FeatureDetail other = (FeatureDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.btl.pojo.FeatureDetail[ id=" + id + " ]";
    }
    
}
