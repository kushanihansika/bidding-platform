package com.wonderSoft.bidding.repositories;


import com.wonderSoft.bidding.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

    Category getCategoryByName(String name);

    @Query(value = "SELECT c.id FROM `Category` c WHERE c.active =1", nativeQuery = true)
    List<String> getAllIds();





}
