package com.wonderSoft.bidding.controllers;


import com.wonderSoft.bidding.dtos.CategoryDTO;
import com.wonderSoft.bidding.dtos.CategoryRenponseDTO;
import com.wonderSoft.bidding.dtos.CategorySubCategoryDTO;
import com.wonderSoft.bidding.services.CategoryService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("api/v1/category-management")
@RestController
public class CategoryController {


    Map<String, Object> response = new HashMap<>();

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDTO category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping(path = "/{id:A\\d{3}}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @Valid @RequestBody CategoryDTO category) {
        category.setId(id);
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        return categoryService.deleteCategory(id);
    }


    @GetMapping("/{id:A\\d{3}}")
    public ResponseEntity<?> getCategoryFromId(@PathVariable("id") String id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/")
    public ResponseEntity<?> getMainCategory() {
        CommonResponse commonResponse = new CommonResponse();
        List<CategoryRenponseDTO> list = categoryService.getAllCategoryList();
        commonResponse.setPayload(Collections.singletonList(list));
        return new ResponseEntity<>(commonResponse,  HttpStatus.OK);
    }

    @GetMapping("/get-category-detailsList")
    public ResponseEntity<?> getCategoryDetailsList() {
        CommonResponse commonResponse = new CommonResponse();
        List<CategorySubCategoryDTO> list = categoryService.getCategoryDetailsList();
        response.put("categorise", list);
        commonResponse.setPayload(Arrays.asList(response));
        return new ResponseEntity<>(commonResponse,  HttpStatus.OK);
    }
}
