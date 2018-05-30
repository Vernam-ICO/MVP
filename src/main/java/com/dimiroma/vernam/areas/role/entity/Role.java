package com.dimiroma.vernam.areas.role.entity;

import com.dimiroma.vernam.enums.RoleType;
import com.dimiroma.vernam.areas.user.entity.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    private Long id;
    private RoleType name;
    private Set<User> users;

    public Role(RoleType name) {
        this.name = name;
        this.users = new HashSet<>();
    }

    public Role() {
        this.users = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    public RoleType getName() {
        return this.name;
    }

    public void setName(final RoleType name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    public Set<User> getUsers() {
        return this.users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }
}
