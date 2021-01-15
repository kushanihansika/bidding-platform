package com.wonderSoft.bidding.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySubCategoryDTO {

       private CategoryDTO category;

       private List<SubCategoryResponseDTO> subCategorise = new ArrayList<>();

}
