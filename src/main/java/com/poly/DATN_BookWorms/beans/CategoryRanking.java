package com.poly.DATN_BookWorms.beans;

import com.poly.DATN_BookWorms.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRanking {
    private Categories categories;
    private int orderNumbers;

    public CategoryRanking(Optional<Categories> byId, int orderNumbers) {
    }
}
