package com.wonderSoft.bidding.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDTO {


    private String subId;
    private String name;
    private String description;
    private Boolean isActive;
    private String categoryId;
    private String imgUrl;


    public SubCategoryDTO(String subId, String name, Boolean isActive, String imgUrl, String id) {
    }
}
