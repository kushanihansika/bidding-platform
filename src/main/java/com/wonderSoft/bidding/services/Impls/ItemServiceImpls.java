package com.wonderSoft.bidding.services.Impls;



import com.wonderSoft.bidding.constants.Status;
import com.wonderSoft.bidding.dtos.ItemDTO;
import com.wonderSoft.bidding.entities.Bids;
import com.wonderSoft.bidding.entities.Item;
import com.wonderSoft.bidding.entities.SubCategory;
import com.wonderSoft.bidding.repositories.BidRepository;
import com.wonderSoft.bidding.repositories.ItemRepository;
import com.wonderSoft.bidding.repositories.SubCategoryRepository;
import com.wonderSoft.bidding.services.ItemService;
import com.wonderSoft.bidding.utils.CommonResponse;
import com.wonderSoft.bidding.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpls implements ItemService {

     private final ItemRepository itemRepository;
     private final SubCategoryRepository subCategoryRepository;
     private BidRepository bidRepository;
    @Autowired
    public ItemServiceImpls(ItemRepository itemRepository, SubCategoryRepository subCategoryRepository, BidRepository bidRepository) {
        this.itemRepository = itemRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.bidRepository = bidRepository;
    }



    @Override
    public ResponseEntity<?> getItemPages(int pageSize, int pageNumber) {
        CommonResponse commonResponse = new CommonResponse();
        List<ItemDTO> itemDTOS = itemRepository.findAll().stream()
                .map(item -> getItemDTO(item)).collect(Collectors.toList());
        commonResponse.setPayload(Collections.singletonList(new Pager(pageSize, pageNumber, itemDTOS)));

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }

    /**
     * ========================================================
     *   This method is responsible save items.
     * ========================================================
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> saveItems(ItemDTO dto) {
         CommonResponse commonResponse = new CommonResponse();

         Map<String, Object> response = new HashMap<>();
         String itemId=(itemRepository.save(getItem(dto)).getId().toString());
                    response.put("itemId",itemId);
            commonResponse.setPayload(Arrays.asList(response));
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    /**
     * ============================================================
     *   This method is responsible update item.
     * ============================================================
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> updateItems(ItemDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!itemRepository.existsById(Long.valueOf(dto.getId()))) {

            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        SubCategory subCategory = subCategoryRepository.getOne(dto.getSubId());
        itemRepository.save(new Item(
                Long.parseLong(dto.getId()),
                dto.getItemName(),
                dto.getItemDescription(),
                dto.getKeywords(),
                dto.getItemImg(),
                dto.getItemPrice(),
                dto.getItemsStatus(),
                dto.getInventory(),
                dto.getPaymentMethod(),
                dto.getCategoryId(),
                subCategory));
        commonResponse.setPayload(Arrays.asList("Successfully Updated..."));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);

    }

    /**
     * ===============================================================
     *  This method is responsible delete item.
     * ===============================================================
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> deleteItems(String id) {
        CommonResponse commonResponse = new CommonResponse();
        Item item = itemRepository.getOne(Long.valueOf(id));
        if (item.getItemsStatus().equals(Status.CLOSED)){
            commonResponse.setErrorMessages(Arrays.asList("Already In deleted status"));
            return new ResponseEntity<>(commonResponse, HttpStatus.ALREADY_REPORTED);
        }
        item.setItemsStatus(Status.CLOSED);
        itemRepository.save(item);
        commonResponse.setErrorMessages(Arrays.asList("Successfully deleted..."));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public CommonResponse getItemsByItemSerial(String id) {
        return null;
    }

    @Override
    public CommonResponse getItemsById(Long id) {
        CommonResponse commonResponse = new CommonResponse();
        Item item = itemRepository.findItemById(id);
        Map<String, Object> response = new HashMap<>();
        ItemDTO itemDTO = getItemDTO(item);
        response.put("itemDetails",itemDTO);
        commonResponse.setPayload(Arrays.asList(response));
        return commonResponse;
    }



    @Override
    public boolean isItemsExists(Long id) {
        return itemRepository.existsById(id);
    }

    /**
     * =========================================================================
     *   This method is responsible change item status.
     * =========================================================================
     * @param id
     * @param status
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> changeItemStatus(String id, String status) {
        CommonResponse commonResponse = new CommonResponse();
        Item item = itemRepository.getOne(Long.valueOf(id));
        item.setItemsStatus(Status.valueOf(status));
        itemRepository.save(item);
        commonResponse.setErrorMessages(Arrays.asList("Item Status Changed.."));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public long ItemsCount() {
        return 0;
    }

    @Override
    public String getLastItemsId() {
        return null;
    }


    public ItemDTO getItemDTO(Item item){
        return new  ItemDTO(
                String.valueOf(item.getId()),
                item.getItemName(),
                item.getItemDescription(),
                item.getItemImg(),
                item.getKeywords(),
                item.getItemPrice(),
                item.getItemsStatus(),
                item.getInventory(),
                item.getPaymentMethod(),
                item.getCategoryId(),
                item.getSubCategory().getSubId(),String.valueOf(item.getBids().getBidsId())
        );
    }


    public Item getItem (ItemDTO dto){
        SubCategory subCategory = subCategoryRepository.findSubCategoryBySubId(dto.getSubId());
        Bids bids = bidRepository.findBidsByBidsId(Long.parseLong(dto.getBidsId()));
        return new Item(
                Long.parseLong(dto.getId()),
                dto.getItemName(),
                dto.getItemDescription(),
                dto.getKeywords(),
                dto.getItemImg(),
                dto.getItemPrice(),
                dto.getItemsStatus(),
                dto.getInventory(),
                dto.getPaymentMethod(),
                dto.getCategoryId(),
                subCategory,bids

        );
    }
}
