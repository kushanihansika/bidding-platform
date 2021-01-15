package com.wonderSoft.bidding.entities;

import com.wonderSoft.bidding.utils.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BidsUserDetails {
    @EmbeddedId
    private BidsUserDetailsPk bidsUserDetailsPk;
    private double bidPrice;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime bidTimeDate;

    @ManyToOne
    @JoinColumn(name="bidId", referencedColumnName = "bidsId",insertable = false,updatable = false)
    private Bids bids;
    @ManyToOne
    @JoinColumn(name="userId",referencedColumnName = "uId",insertable = false, updatable = false)
    private UserAccount userAccount;

    public BidsUserDetails(BidsUserDetailsPk bidsUserDetailsPk, double bidPrice, LocalDateTime bidTimeDate) {
        this.bidsUserDetailsPk = bidsUserDetailsPk;
        this.bidPrice = bidPrice;
        this.bidTimeDate = bidTimeDate;
    }

    public BidsUserDetails(long bidId,String userId, double bidPrice, LocalDateTime bidTimeDate) {
        this.bidsUserDetailsPk = new BidsUserDetailsPk(bidId,userId);
        this.bidPrice = bidPrice;
        this.bidTimeDate = bidTimeDate;
    }
}
