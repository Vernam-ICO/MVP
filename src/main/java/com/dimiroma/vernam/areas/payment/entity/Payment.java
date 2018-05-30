package com.dimiroma.vernam.areas.payment.entity;

import com.dimiroma.vernam.areas.policy.entity.Policy;
import com.dimiroma.vernam.areas.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {
    private Long id;
    private User user;
    private Policy policy;
    private Date creationDate;

    public Payment() {
        this.creationDate = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public User getUser() {
        return this.user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "policyId")
    public Policy getPolicy() {
        return this.policy;
    }

    public void setPolicy(final Policy policy) {
        this.policy = policy;
    }

    @Column(name = "creationDate", nullable = false)
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }
}
