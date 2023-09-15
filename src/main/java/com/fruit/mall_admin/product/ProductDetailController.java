package com.fruit.mall_admin.product;

import com.fruit.mall_admin.product.dto.ProductDetail;
import com.fruit.mall_admin.review.ReviewService;
import com.fruit.mall_admin.review.dto.DetailReviewDto;
import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductService productService;
    private final ReviewService reviewService;

    @GetMapping("/detail/{productId}")
    public String showDetail(Model model, @PathVariable("productId") Long productId) {
        ProductDetail productDetail = productService.selectProductDetailByProductId(productId);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productId", productId);
        return "admin/detail";
    }

    @GetMapping("/detail/review/{productId}")
    public String goReview(Model model,
                           @PathVariable("productId") Long productId,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        PageInfo<DetailReviewDto> pageInfo = reviewService.getReviewsByProductId(productId, pageNum, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        ProductDetail productDetail = productService.selectProductDetailByProductId(productId);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productId", productId);
        return "admin/detailReview";
    }
}
