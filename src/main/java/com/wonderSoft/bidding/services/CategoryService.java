package com.wonderSoft.bidding.services;

import com.wonderSoft.bidding.dtos.CategoryDTO;
import com.wonderSoft.bidding.dtos.CategoryRenponseDTO;
import com.wonderSoft.bidding.dtos.CategorySubCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategory();

   ResponseEntity<?> saveCategory(CategoryDTO dto);

    ResponseEntity<?> updateCategory(CategoryDTO dto);

    ResponseEntity<?> deleteCategory(String id);

    ResponseEntity<?> getCategoryById(String id);

    boolean isCategoryExists(String id);

    boolean isExistByCategoryName(String categoryName);

    long categoryCount();


    List<CategoryRenponseDTO> getAllCategoryList();

    List<CategorySubCategoryDTO> getCategoryDetailsList();
}