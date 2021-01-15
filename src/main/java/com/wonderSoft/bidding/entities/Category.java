package com.wonderSoft.bidding.entities;

import com.wonderSoft.bidding.utils.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Category  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idCategory_seq")
    @GenericGenerator(
            name = "idCategory_seq",
            strategy = "com.wonderSoft.bidding.utils.SnowflakeIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "A"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String id;
    private String name;
    private Integer active;
    private String imgUrl;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<SubCategory> subCategories = new ArrayList<>();

    public Category(String id, String name, Integer active, String imgUrl) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.imgUrl = imgUrl;
    }


    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void addSubCategory(SubCategory subCategory) {
        this.getSubCategories().add(subCategory);
        subCategory.setCategory(this);
    }

}
