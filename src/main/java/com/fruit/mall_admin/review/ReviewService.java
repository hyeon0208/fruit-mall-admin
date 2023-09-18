package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.DetailReviewDto;
import com.fruit.mall_admin.review.dto.ReviewCountDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.fruit.mall_admin.review.dto.ReviewSearchCond;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public PageInfo<ReviewResDto> getReviews(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "NUM DESC");
        List<ReviewResDto> reviews = reviewRepository.selectAllReview();
        return new PageInfo<>(reviews);
    }

    public PageInfo<ReviewResDto> getReviewsBySearchCond(int pageNum, int pageSize, ReviewSearchCond cond) {
        PageHelper.startPage(pageNum, pageSize, "NUM DESC");
        List<ReviewResDto> reviews = reviewRepository.selectAllBySearchCond(cond);
        return new PageInfo<>(reviews);
    }

    public PageInfo<DetailReviewDto> getReviewsByProductId(Long productId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "REVIEW_CREATED_AT DESC");
        List<DetailReviewDto> reviews = reviewRepository.selectAllByProductId(productId);
        return new PageInfo<>(reviews);
    }

    public void deleteByReviewId(Long reviewId) {
        reviewRepository.deleteByReviewId(reviewId);
    }

    public ReviewCountDto countReviews() {
        return reviewRepository.countReviews();
    }
}
