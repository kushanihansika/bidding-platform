package com.wonderSoft.bidding.entities;

import com.wonderSoft.bidding.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
public class SubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idSubCategory_seq")
    @GenericGenerator(
            name = "idSubCategory_seq",
            strategy = "com.wonderSoft.bidding.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "0"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "B"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
            })
    private String subId;
    private String name;
    private String description;
    private Boolean isActive;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name="categoryId",referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    public SubCategory(String name, String description, Boolean isActive, String imgUrl, Category category) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.imgUrl = imgUrl;
        this.category = category;
    }

    public SubCategory(String subId, String name, String description, Boolean isActive, String imgUrl, Category category) {
        this.subId = subId;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.imgUrl = imgUrl;
        this.category = category;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addSubCategory(Item item) {
        this.getItems().add(item);
        item.setSubCategory(this);
    }

}
