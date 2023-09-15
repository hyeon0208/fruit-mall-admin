package com.fruit.mall_admin.review;

import com.fruit.mall_admin.review.dto.ReviewCountDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/review")
    public String reviewPaging(Model model,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<ReviewResDto> pageInfo = reviewService.getReviews(pageNum, pageSize);
        ReviewCountDto countDto = reviewService.countReviews();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("totalCount", countDto.getTotalReviewCount());
        model.addAttribute("repliedCount", countDto.getCompletedRepliedCount());
        model.addAttribute("notRepliedCount", countDto.getNotRepliedCount());
        return "admin/review";
    }
}
