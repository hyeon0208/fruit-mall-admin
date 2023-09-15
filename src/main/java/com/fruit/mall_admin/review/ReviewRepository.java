package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.ReviewCountDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository implements ReviewMapper {
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewResDto> selectAllReview() {
        return reviewMapper.selectAllReview();
    }

    @Override
    public ReviewCountDto countReviews() {
        return reviewMapper.countReviews();
    }
}
