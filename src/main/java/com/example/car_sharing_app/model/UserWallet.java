package com.example.car_sharing_app.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users_wallet")
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Column(name = "user_id")*/
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "currency")
    private String currency;

    @Column(name = "value")
    private Double value;

    @OneToMany
    @JoinColumn(name = "wallet_id")
    private List<UserWalletHistory> walletHistories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<UserWalletHistory> getWalletHistories() {
        return walletHistories;
    }

    public void setWalletHistories(List<UserWalletHistory> walletHistories) {
        this.walletHistories = walletHistories;
    }
}
