package com.wonderSoft.bidding.repositories;
import com.wonderSoft.bidding.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findItemById(Long id);


}
