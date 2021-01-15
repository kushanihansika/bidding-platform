package com.wonderSoft.bidding.services;


import com.wonderSoft.bidding.dtos.ItemDTO;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {

    ResponseEntity<?> getItemPages(int pageNumber, int pageSize);

    List<ItemDTO> getAllItems();

    ResponseEntity<?> saveItems(ItemDTO dto);

    ResponseEntity<?> updateItems(ItemDTO dto);

    ResponseEntity<?> deleteItems(String id);

    CommonResponse getItemsByItemSerial(String id);

    CommonResponse getItemsById(Long id);



    long ItemsCount();

    String getLastItemsId();

    boolean isItemsExists(Long id);

    ResponseEntity<?> changeItemStatus(String id, String status);

}
