package com.wonderSoft.bidding.entities;

import com.wonderSoft.bidding.constants.Status;
import com.wonderSoft.bidding.utils.LocalDateTimeConverter;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Bids {

    @Id
    @Column(name = "bidsId")
    @GeneratedValue(generator = "snowflake")
    @GenericGenerator(name = "snowflake", strategy = "com.wonderSoft.bidding.utils.SnowflakeIdGenerator")
    private Long bidsId;
    private String bidsUsersId;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdTimeDate;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime closedTimeDate;
    private Status bidStatus;

    @OneToMany(mappedBy = "bids", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "bids", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<BidsUserDetails> bidsUserDetails = new ArrayList<>();

    public Bids(Long bidsId, String bidsUsersId, LocalDateTime createdTimeDate, LocalDateTime closedTimeDate, Status bidStatus) {
        this.bidsId = bidsId;
        this.bidsUsersId = bidsUsersId;
        this.createdTimeDate = createdTimeDate;
        this.closedTimeDate = closedTimeDate;
        this.bidStatus = bidStatus;
    }

    public void addItems(Item order) {
        this.getItems().add(order);
        order.setBids(this);
    }

}
