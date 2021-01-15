package com.wonderSoft.bidding.dtos;


import com.wonderSoft.bidding.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private String id;
    private String itemName;
    private String itemDescription;
    private String keywords;
    private String itemImg;
    private double itemPrice;
    private Status itemsStatus;
    private int inventory;
    private Integer paymentMethod;
    private String categoryId;
    private String subId;
    private String bidsId;


}
