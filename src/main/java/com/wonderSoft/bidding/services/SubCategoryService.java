package com.wonderSoft.bidding.services;



import com.wonderSoft.bidding.dtos.SubCategoryDTO;
import com.wonderSoft.bidding.dtos.SubCategoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubCategoryService {

    List<SubCategoryDTO> getAllSubCategory();

    ResponseEntity<?> saveSubCategory(SubCategoryDTO dto);

    ResponseEntity<?> updateSubCategory(SubCategoryDTO dto);

    ResponseEntity<?> deleteSubCategory(String id);

    SubCategoryDTO getSubCategoryById(String id);

    ResponseEntity<?> changeStatus(String id, Boolean isActive);

    boolean isSubCategoryExists(String id);

    long SubCategoryCount();

    /**
     * get all subcategory for given categoryId
     * @Params categoryId
     */
    List<SubCategoryResponseDTO> getSubCategoryByCategory(String categoryId);


}
