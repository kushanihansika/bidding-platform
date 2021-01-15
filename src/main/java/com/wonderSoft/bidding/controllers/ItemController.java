package com.wonderSoft.bidding.controllers;


import com.wonderSoft.bidding.dtos.ItemDTO;
import com.wonderSoft.bidding.services.ItemService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RequestMapping("api/v1/items-management")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping("/")
    public ResponseEntity<?> saveItem(@RequestBody ItemDTO itemDTO) {
        return itemService.saveItems(itemDTO);
    }

    @GetMapping("/get-item/{id}")
    public CommonResponse getItem(@PathVariable("id") String id){
        CommonResponse commonResponse = new CommonResponse();
        Long itemId = Long.parseLong(id);
        if(!itemService.isItemsExists(itemId)){
            commonResponse.setErrorMessages(Arrays.asList("Not found Item"));
            return commonResponse;
        }
        commonResponse =itemService.getItemsById(itemId);
        return commonResponse;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable("id") String id, @RequestBody ItemDTO dto) {
        dto.setId(id);
        return itemService.updateItems(dto);
    }

    @PostMapping("/change-Item-Status/{id}")
    public ResponseEntity<?> changeItemStatus(@PathVariable("id") String id, @Param("status") String status){
        CommonResponse commonResponse = new CommonResponse();
        if (!itemService.isItemsExists(Long.valueOf(id)) || id.equals(null)){
            commonResponse.setErrorMessages(Arrays.asList("please enter valid id", "Item not exist"));
            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        else{
            return itemService.changeItemStatus(id,status);
        }
    }

    @PostMapping("/closed-Bid-Item/{id}")
    public ResponseEntity<?> clodedBidItem(@PathVariable("id") String id){
        CommonResponse commonResponse = new CommonResponse();
        if (!itemService.isItemsExists(Long.valueOf(id)) || id.equals(null)){
            commonResponse.setErrorMessages(Arrays.asList("please enter valid id", "Item not exist"));
        }
        return itemService.deleteItems(id);
    }

    @GetMapping(path = "/",params = {"pageNumber","pageSize"})
    public ResponseEntity<?> getItemPages(int pageNumber, int pageSize){
        return itemService.getItemPages(pageNumber,pageSize);
    }

}
