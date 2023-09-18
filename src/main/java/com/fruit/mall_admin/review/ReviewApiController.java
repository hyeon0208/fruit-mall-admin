package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.fruit.mall_admin.review.dto.ReviewSearchCond;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReviewApiController {
    private final ReviewService reviewService;

    @GetMapping("/review/searchFilter")
    public PageInfo<ReviewResDto> showFileteredPage(
            @ModelAttribute ReviewSearchCond cond,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<ReviewResDto> pageInfo = reviewService.getReviewsBySearchCond(pageNum, pageSize, cond);
        return pageInfo;
    }

    @PostMapping("/review/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteByReviewId(reviewId);
        return ResponseEntity.ok().build();
    }
}
