package com.vorobey.userservice.util;

import com.vorobey.productservice.entity.ProductEntity;
import lombok.Data;

@Data
public class ProductWithStock {
    private ProductEntity product;
    private int stock;
}
