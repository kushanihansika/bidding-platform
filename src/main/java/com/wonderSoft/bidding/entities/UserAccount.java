package com.wonderSoft.bidding.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class UserAccount {
    @Id
    @Column(name = "uId")
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.wonderSoft.bidding.utils.SnowflakeIdGenerator")
    private String uId;
    private String name;
    private String role;
    @OneToMany(mappedBy = "userAccount", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Item> bidItems = new ArrayList<>();

    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<BidsUserDetails> orderDetails = new ArrayList<>();

    public UserAccount(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public UserAccount(String uId, String name, String role) {
        this.uId = uId;
        this.name = name;
        this.role = role;
    }

    public void addItems(Item item) {
        this.getBidItems().add(item);
        item.setUserAccount(this);
    }
}
