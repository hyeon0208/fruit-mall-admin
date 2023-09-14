package com.fruit.mall_admin.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CountOfProductsResDto {
    private int totalCount;
    private int onSaleCount;
    private int offSaleCount;
    private int soldOutCount;

    @Builder
    public CountOfProductsResDto(int totalCount, int onSaleCount, int offSaleCount, int soldOutCount) {
        this.totalCount = totalCount;
        this.onSaleCount = onSaleCount;
        this.offSaleCount = offSaleCount;
        this.soldOutCount = soldOutCount;
    }
}
