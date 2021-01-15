package com.wonderSoft.bidding.repositories;

import com.wonderSoft.bidding.entities.Bids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bids,Long> {

    Bids findBidsByBidsId(Long id);
}
