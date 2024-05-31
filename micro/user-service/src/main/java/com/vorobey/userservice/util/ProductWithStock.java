package com.vorobey.userservice.util;

import com.vorobey.productservice.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductWithStock {
    private ProductEntity product;
    private int stock;
}
