package com.example.car_sharing_app.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_wallet_history")
public class UserWalletHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "transaction_date")
    private LocalDate transaction_date;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
