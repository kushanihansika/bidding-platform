package com.wonderSoft.bidding.entities;

import com.wonderSoft.bidding.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.wonderSoft.bidding.utils.SnowflakeIdGenerator")
    private Long id;
    private String itemName;
    private String itemDescription;
    private String keywords;
    private String itemImg;
    private double itemPrice;

    @Enumerated(EnumType.STRING)
    private Status itemsStatus;

    private int inventory;
    private Integer  paymentMethod;
    private  String categoryId;


    @ManyToOne
    @JoinColumn(name="subId",referencedColumnName = "subId")
    private SubCategory subCategory;

    @ManyToOne
    @JoinColumn(name="bidsId",referencedColumnName = "bidsId")
    private Bids bids;

    @ManyToOne
    @JoinColumn(name="uId",referencedColumnName = "uId")
    private UserAccount userAccount;


    public Item(Long id, String itemName, String itemDescription, String keywords, String itemImg, double itemPrice, Status itemsStatus, int inventory, Integer paymentMethod, String categoryId, SubCategory subCategory, Bids bids) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.keywords = keywords;
        this.itemImg = itemImg;
        this.itemPrice = itemPrice;
        this.itemsStatus = itemsStatus;
        this.inventory = inventory;
        this.paymentMethod = paymentMethod;
        this.categoryId = categoryId;
        this.subCategory = subCategory;
        this.bids = bids;
    }

    public Item(Long id, String itemName, String itemDescription, String keywords, String itemImg, double itemPrice, Status itemsStatus, int inventory, Integer paymentMethod, String categoryId, SubCategory subCategory) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.keywords = keywords;
        this.itemImg = itemImg;
        this.itemPrice = itemPrice;
        this.itemsStatus = itemsStatus;
        this.inventory = inventory;
        this.paymentMethod = paymentMethod;
        this.categoryId = categoryId;
        this.subCategory = subCategory;
    }

}
