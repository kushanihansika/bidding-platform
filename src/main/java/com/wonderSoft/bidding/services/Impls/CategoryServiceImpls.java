package com.wonderSoft.bidding.services.Impls;


import com.wonderSoft.bidding.dtos.CategoryDTO;
import com.wonderSoft.bidding.dtos.CategoryRenponseDTO;
import com.wonderSoft.bidding.dtos.CategorySubCategoryDTO;
import com.wonderSoft.bidding.dtos.SubCategoryResponseDTO;
import com.wonderSoft.bidding.entities.Category;
import com.wonderSoft.bidding.repositories.CategoryRepository;
import com.wonderSoft.bidding.services.CategoryService;
import com.wonderSoft.bidding.services.SubCategoryService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpls implements CategoryService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryDTO> getAllCategory() {
        return null;
    }

    @Override
    public ResponseEntity<CommonResponse> saveCategory(CategoryDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (isExistByCategoryName(dto.getName())) {

            return new ResponseEntity<>(commonResponse, HttpStatus.CONFLICT);
        }
        Map<String, Object> response = new HashMap<>();
            String categoryId = categoryRepository.save(new Category(dto.getId(), dto.getName(), dto.getActive(), dto.getImgUrl())).getId();
            response.put("CategoryId", categoryId);
            commonResponse.setPayload(Arrays.asList(response));

        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<CommonResponse> updateCategory(CategoryDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!categoryService.isCategoryExists(dto.getId())) {
            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        categoryRepository.save(new Category(dto.getId(), dto.getName(), dto.getActive(), dto.getImgUrl()));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> deleteCategory(String id) {
        CommonResponse commonResponse = new CommonResponse();
        Category category = categoryRepository.getOne(id);
        if (!categoryService.isCategoryExists(id)){

            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
//        if (category.getActive() == 0) {
//            commonResponse.setErrorMessages(Arrays.asList(CategoryValidationMassages.AlREADY_DELETED_CATEGORY));
//            commonResponse.setStatus(CommonConst.CONFLICT);
//            return commonResponse;
//        }
        category.setActive(0);
        categoryRepository.save(category);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse> getCategoryById(String id) {
        CommonResponse commonResponse = new CommonResponse();
        if (!isCategoryExists(id)){

            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.getOne(id);
        CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getActive(), category.getImgUrl());
        Map<String, Object> response = new HashMap<>();
        response.put("Category", categoryDTO);
        commonResponse.setPayload(Arrays.asList(response));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public boolean isCategoryExists(String id) {
        return categoryRepository.existsById(id);
    }

    public boolean isExistByCategoryName(String categoryName) {
        Boolean isExist = false;
        Category category = categoryRepository.getCategoryByName(categoryName);
        if (category != null) {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public long categoryCount() {
        return categoryRepository.count();
    }

    @Override
    public List<CategoryRenponseDTO> getAllCategoryList() {
        List<CategoryRenponseDTO> categoryRenponseDTOS = categoryRepository
                .findAll().stream()
                .filter(category -> category.getActive()==1)
                .map(category -> new CategoryRenponseDTO(category.getId(), category.getName()))
                .collect(Collectors.toList());
        return categoryRenponseDTOS;
    }


    @Override
    public List<CategorySubCategoryDTO> getCategoryDetailsList() {
        List<String> list = categoryRepository.getAllIds();
        List<CategorySubCategoryDTO> categorySubCategory = new ArrayList<>();
        list.forEach(id -> {
            CategorySubCategoryDTO categorySubCategoryDTO = new CategorySubCategoryDTO();
            Category category = categoryRepository.getOne(id);
            CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName(), category.getActive(), category.getImgUrl());
            List<SubCategoryResponseDTO> subs = subCategoryService.getSubCategoryByCategory(id);
            categorySubCategoryDTO.setCategory(categoryDTO);
            categorySubCategoryDTO.setSubCategorise(subs);
            categorySubCategory.add(categorySubCategoryDTO);
        });
        return categorySubCategory;
    }
}
