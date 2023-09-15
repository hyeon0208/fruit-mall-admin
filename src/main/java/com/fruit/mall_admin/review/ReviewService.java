package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.ReviewCountDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
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

    public ReviewCountDto countReviews() {
        return reviewRepository.countReviews();
    }
}
