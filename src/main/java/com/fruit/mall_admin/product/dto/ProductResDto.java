package com.fruit.mall_admin.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ProductResDto {
    private Long productId;
    private Long categoryId;
    private String productName;
    private int productPrice;
    private int productStock;
    private int productDiscount;
    private String productSaleStatus;
    private Timestamp productCreatedAt;
    private int likeCount;
    private int paymentCount;
    private int reviewCount;

    @Builder
    public ProductResDto(Long productId, Long categoryId, String productName, int productPrice, int productStock, int productDiscount, String productSaleStatus, Timestamp productCreatedAt, int likeCount, int paymentCount, int reviewCount) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDiscount = productDiscount;
        this.productSaleStatus = productSaleStatus;
        this.productCreatedAt = productCreatedAt;
        this.likeCount = likeCount;
        this.paymentCount = paymentCount;
        this.reviewCount = reviewCount;
    }
}
