package com.wonderSoft.bidding.repositories;

import com.wonderSoft.bidding.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,String> {

    List<SubCategory> getSubCategoriesByCategoryId(String categoryId);

    SubCategory findSubCategoryBySubId(String subId);

}
