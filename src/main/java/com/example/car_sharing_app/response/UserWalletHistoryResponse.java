package com.example.car_sharing_app.response;

import com.example.car_sharing_app.helper.DateHelper;
import com.example.car_sharing_app.model.UserWalletHistory;
import lombok.Data;

@Data
public class UserWalletHistoryResponse {

    private Integer id;
    private String transaction_date;
    private String type;
    private Double value;

    public UserWalletHistoryResponse(UserWalletHistory userWalletHistory) {
        this.id = userWalletHistory.getId();
        this.transaction_date = DateHelper.dateToString(userWalletHistory.getTransaction_date());
        this.type = userWalletHistory.getType();
        this.value = userWalletHistory.getValue();
    }
}
