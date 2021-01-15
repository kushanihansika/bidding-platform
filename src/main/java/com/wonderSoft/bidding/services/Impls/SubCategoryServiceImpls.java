package com.wonderSoft.bidding.services.Impls;


import com.wonderSoft.bidding.dtos.SubCategoryDTO;
import com.wonderSoft.bidding.dtos.SubCategoryResponseDTO;
import com.wonderSoft.bidding.entities.Category;
import com.wonderSoft.bidding.entities.SubCategory;
import com.wonderSoft.bidding.repositories.CategoryRepository;
import com.wonderSoft.bidding.repositories.SubCategoryRepository;
import com.wonderSoft.bidding.services.SubCategoryService;
import com.wonderSoft.bidding.utils.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpls implements SubCategoryService {

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpls(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public List<SubCategoryDTO> getAllSubCategory() {
        return null;
    }

    /**
     * ===================================================
     *  This method is responsible save subcategory.
     * ===================================================
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> saveSubCategory(SubCategoryDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!categoryRepository.existsById(dto.getCategoryId())) {
            return  new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        } else {
            Category category = categoryRepository.getOne(dto.getCategoryId());
            String subCategoryId = subCategoryRepository.save(new SubCategory(dto.getName(), dto.getDescription(), dto.getIsActive(), dto.getImgUrl(), category)).getSubId();
            Map<String, Object> response = new HashMap<>();
            response.put("subCategoryId", subCategoryId);
            commonResponse.setPayload(Arrays.asList(response));
        }
        return  new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    /**
     * ==================================================
     *  This method is responsible update subcategory.
     * ==================================================
     * @param dto
     * @return
     */
    @Override
    public ResponseEntity<CommonResponse> updateSubCategory(SubCategoryDTO dto) {
        CommonResponse commonResponse = new CommonResponse();
        if (!subCategoryRepository.existsById(dto.getSubId())) {

            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.getOne(dto.getCategoryId());
        subCategoryRepository.save(new SubCategory(
                dto.getSubId(),
                dto.getName(),
                dto.getDescription(),
                dto.getIsActive(),
                dto.getImgUrl(),
                category));

        commonResponse.setPayload(Arrays.asList("Successfully Updated..."));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSubCategory(String id) {
        return null;
    }

    /**
     * ========================================================
     *  This method is responsible get subcategory using id.
     * ========================================================
     * @param id
     * @return
     */
    @Override
    public SubCategoryDTO getSubCategoryById(String id) {
        SubCategory subCategory = subCategoryRepository.getOne(id);
        SubCategoryDTO dto = new SubCategoryDTO(subCategory.getSubId(),subCategory.getName(),subCategory.getIsActive(),subCategory.getImgUrl(),subCategory.getCategory().getId());
        return dto;
    }

    @Override
    public ResponseEntity<CommonResponse> changeStatus(String id, Boolean isActive) {
        CommonResponse commonResponse = new CommonResponse();
        if (!subCategoryRepository.existsById(id)){
            return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
        }
        SubCategory subCategory = subCategoryRepository.getOne(id);
        subCategory.setIsActive(isActive);
        subCategoryRepository.save(subCategory);
        commonResponse.setPayload(Arrays.asList("Status changed...."));
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public boolean isSubCategoryExists(String id) {
        return subCategoryRepository.existsById(id);
    }

    @Override
    public long SubCategoryCount() {
        return subCategoryRepository.count();
    }

    @Override
    public List<SubCategoryResponseDTO> getSubCategoryByCategory(String categoryId) {
        List<SubCategoryResponseDTO> subCategoryResponseDTOS = subCategoryRepository
                .getSubCategoriesByCategoryId(categoryId)
                .stream()
                .map(subCategory -> new SubCategoryResponseDTO(subCategory.getSubId(), subCategory.getName()))
                .collect(Collectors.toList());
        return subCategoryResponseDTOS;
    }


}
