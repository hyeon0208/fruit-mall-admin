package com.fruit.mall_admin.review.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewSearchCond {
    private String status;
    private int duration;
    private String startDate;
    private String endDate;
    private String category;
    private String searchText;
    private int pageNum;
    private int pageSize;
}
