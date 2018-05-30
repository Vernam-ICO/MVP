package com.dimiroma.vernam.areas.user.entity;

import com.dimiroma.vernam.enums.RoleType;
import com.dimiroma.vernam.areas.payment.entity.Payment;
import com.dimiroma.vernam.areas.role.entity.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String address;
    private Long tokens;
    private Set<Payment> payments;
    private Set<Role> roles;
    private boolean active;

    public User(final String email, final String firstName, final String lastName, final String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

        this.payments = new HashSet<>();
        this.roles = new HashSet<>();
        this.active = true;
    }

    public User() {
        this.payments = new HashSet<>();
        this.roles = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    @Column(name = "firsName", nullable = false)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles")
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Column(name = "tokens")
    public Long getTokens() {
        return this.tokens;
    }

    public void setTokens(final Long tokens) {
        this.tokens = tokens;
    }

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    public Set<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @Column(nullable = true)
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void addTokens(final Long tokens) {
        if (this.tokens == null) {
            this.tokens = 0L;
        }
        this.tokens += tokens;
    }

    @Transient
    public boolean isAdmin() {
        return this.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals(RoleType.ROLE_ADMIN));
    }
}
