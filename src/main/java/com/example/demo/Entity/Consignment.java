package com.example.demo.Entity;

import com.example.demo.Entity.enums.ConsignmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consignment")
@Getter
@Setter
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "consignment_date")
    private LocalDate consignmentCreateDate;

    @Column(name = "consignment_status")
    @Enumerated(EnumType.STRING)
    ConsignmentStatus consignmentStatus;

    @Column(name = "consignment_price")
    private float consignmentSuggestionPrice;

    @Column(name = "consignment_description")
    private String consignmentDes;

    @Column(name = "consignment_Fee")
    private float consignmentConfirmedPrice;

    private LocalDate consignmentSignDate;

    @JsonIgnore
    private boolean isDeleted=false;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties({"id","password","role","koiOrders","username","authorities","enabled","accountNonLocked","accountNonExpired","credentialsNonExpired"})
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "koi_id", referencedColumnName = "id")
    @JsonManagedReference
    private Koi koi;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
//    @JsonManagedReference
    @JoinTable(name = "consignment_payment",
            joinColumns = @JoinColumn(name = "consignmentId"),
            inverseJoinColumns = @JoinColumn(name = "paymentMethodId"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
