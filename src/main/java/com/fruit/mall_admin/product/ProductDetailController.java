package com.fruit.mall_admin.product;

import com.fruit.mall_admin.product.dto.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductDetailController {
    private final ProductService productService;

    @GetMapping("/detail/{productId}")
    public String showDetail(Model model, @PathVariable("productId") Long productId) {
        ProductDetail productDetail = productService.selectProductDetailByProductId(productId);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("productId", productId);

        return "admin/detail";
    }
}
