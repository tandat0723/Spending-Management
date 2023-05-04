/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btl.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author phuan
 */
@Entity
@Table(name = "group_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupTransaction.findAll", query = "SELECT g FROM GroupTransaction g"),
    @NamedQuery(name = "GroupTransaction.findById", query = "SELECT g FROM GroupTransaction g WHERE g.id = :id"),
    @NamedQuery(name = "GroupTransaction.findByName", query = "SELECT g FROM GroupTransaction g WHERE g.name = :name"),
    @NamedQuery(name = "GroupTransaction.findByDescription", query = "SELECT g FROM GroupTransaction g WHERE g.description = :description"),
    @NamedQuery(name = "GroupTransaction.findByPrice", query = "SELECT g FROM GroupTransaction g WHERE g.price = :price"),
    @NamedQuery(name = "GroupTransaction.findByActive", query = "SELECT g FROM GroupTransaction g WHERE g.active = :active")})
public class GroupTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private int active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private Set<GroupUsers> groupUsersSet;
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User creatorId;

    public GroupTransaction() {
    }

    public GroupTransaction(Integer id) {
        this.id = id;
    }

    public GroupTransaction(Integer id, String name, int active) {
        this.id = id;
        this.name = name;
        this.active = active;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @XmlTransient
    public Set<GroupUsers> getGroupUsersSet() {
        return groupUsersSet;
    }

    public void setGroupUsersSet(Set<GroupUsers> groupUsersSet) {
        this.groupUsersSet = groupUsersSet;
    }

    public User getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(User creatorId) {
        this.creatorId = creatorId;
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
        if (!(object instanceof GroupTransaction)) {
            return false;
        }
        GroupTransaction other = (GroupTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.btl.pojo.GroupTransaction[ id=" + id + " ]";
    }
    
}
