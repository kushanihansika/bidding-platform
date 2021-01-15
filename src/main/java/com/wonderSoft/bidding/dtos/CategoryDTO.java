package com.wonderSoft.bidding.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {


    private String id;
    private String name;
    private Integer active;
    private String imgUrl;
}
