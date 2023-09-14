package com.fruit.mall_admin.product.dto;

import com.github.pagehelper.PageInfo;
import lombok.Getter;

@Getter
public class PageResDto {
    private PageInfo<ProductResDto> productPageInfo;
    private String status;
    private String category;

    public PageResDto(PageInfo<ProductResDto> pageInfo, String status, String category) {
        this.productPageInfo = pageInfo;
        this.status = status;
        this.category = category;
    }
}
