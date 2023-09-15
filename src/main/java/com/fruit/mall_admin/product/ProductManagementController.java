package com.fruit.mall_admin.product;

import com.fruit.mall_admin.image.ImageService;
import com.fruit.mall_admin.product.dto.CountOfProductsResDto;
import com.fruit.mall_admin.product.dto.ProductResDto;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductManagementController {
    private final ProductService productService;
    private final ImageService imageService;

    @GetMapping("/product")
    public String paging(Model model,
                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageInfo<ProductResDto> pageInfo = productService.getProducts(pageNum, pageSize);
        CountOfProductsResDto dto = productService.countOfProductsByStatus();

        model.addAttribute("totalCount", dto.getTotalCount());
        model.addAttribute("onSaleCount", dto.getOnSaleCount());
        model.addAttribute("offSaleCount", dto.getOffSaleCount());
        model.addAttribute("soldOutCount", dto.getSoldOutCount());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/product";
    }

    @GetMapping("/edit/product/{productId}")
    public String editProductForm(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.selectProductAllById(productId);
        String productImage = imageService.selectProductImageUrlByProductId(product.getProductId());

        model.addAttribute("product", product);
        model.addAttribute("productImage", productImage);
        return "admin/editForm";
    }
}
