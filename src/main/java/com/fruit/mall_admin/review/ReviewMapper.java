package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.DetailReviewDto;
import com.fruit.mall_admin.review.dto.ReviewCountDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewResDto> selectAllReview();

    List<DetailReviewDto> selectAllByProductId(@Param("productId") Long productId);

    ReviewCountDto countReviews();
}
