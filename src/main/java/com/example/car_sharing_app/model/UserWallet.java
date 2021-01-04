package com.example.car_sharing_app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_wallet")
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "value", nullable = false)
    private Double value;
}
