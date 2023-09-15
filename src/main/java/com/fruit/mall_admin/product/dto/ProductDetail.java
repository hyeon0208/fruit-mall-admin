package com.fruit.mall_admin.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetail {
    private Long productId;
    private String productName;
    private int productPrice;
    private int productDiscount;
    private int productStock;
    private String productDescription;
    private String imageUrl;
}
