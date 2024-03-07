package com.poly.DATN_BookWorms.responses;

import com.poly.DATN_BookWorms.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRanking {
    private Category category;
    private int orderNumbers;
}
