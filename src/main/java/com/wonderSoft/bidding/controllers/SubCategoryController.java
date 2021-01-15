package com.wonderSoft.bidding.controllers;


import com.wonderSoft.bidding.dtos.SubCategoryDTO;
import com.wonderSoft.bidding.dtos.SubCategoryResponseDTO;
import com.wonderSoft.bidding.services.CategoryService;
import com.wonderSoft.bidding.services.SubCategoryService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RequestMapping("api/v1/subCategory-management")
@RestController
public class SubCategoryController {

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    CategoryService categoryService;


    @PostMapping("/")
    public ResponseEntity<?> saveSubCategory(@RequestBody SubCategoryDTO subCategory) {
        return subCategoryService.saveSubCategory(subCategory);
    }

    @PutMapping("/{id:B\\d{3}}")
    public ResponseEntity<?> updateSubCategory(@PathVariable("id") String id, @RequestBody SubCategoryDTO subCategory) {
        subCategory.setSubId(id);
        return subCategoryService.updateSubCategory(subCategory);
    }

    @GetMapping("/{id:A\\d{3}}")
    public ResponseEntity<?> getSubCategoryFromMain(@PathVariable("id") String id) {
        CommonResponse commonResponse = new CommonResponse();
        if (!categoryService.isCategoryExists(id)) {
            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        List<SubCategoryResponseDTO> list = subCategoryService.getSubCategoryByCategory(id);
        commonResponse.setPayload(Collections.singletonList(list));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PostMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable("id") String id, @Param("active") Boolean isActive){
        return subCategoryService.changeStatus(id,isActive);
    }


}
