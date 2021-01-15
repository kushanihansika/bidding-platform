package com.wonderSoft.bidding.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class BidsUserDetailsPk implements Serializable {
    private Long bidId;
    private String userId;


}
