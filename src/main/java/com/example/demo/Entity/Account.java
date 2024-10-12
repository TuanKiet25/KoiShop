package com.example.demo.Entity;

import com.example.demo.Entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String email;
    String password;
    String fullName;
    String phone;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "account")
    private Set<Consignment> consignments = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<KoiOrder> koiOrders = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }



}
