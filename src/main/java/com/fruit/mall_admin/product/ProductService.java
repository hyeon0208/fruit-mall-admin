package com.fruit.mall_admin.product;

import com.fruit.mall_admin.product.dto.CountOfProductsResDto;
import com.fruit.mall_admin.product.dto.ProductAndImageInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public PageInfo<Product> getProducts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "PRODUCT_ID DESC");
        List<Product> products = productRepository.selectAll();
        return new PageInfo<>(products);
    }

    public PageInfo<Product> getProductsByFilter(int pageNum, int pageSize, String status, String category, String searchCond) {
        PageHelper.startPage(pageNum, pageSize, "PRODUCT_ID DESC");
        List<Product> products = productRepository.selectAllByFilter(status, category, searchCond);
        return new PageInfo<>(products);
    }

    public PageInfo<ProductAndImageInfo> getProductsAndImageByFilter(int pageNum, int pageSize, String category, String searchCond, Long userId) {
        PageHelper.startPage(pageNum, pageSize, "PRODUCT_ID DESC");
        List<ProductAndImageInfo> productAndImageInfos = productRepository.selectProductAndImageByFilter(category, searchCond, userId);
        return new PageInfo<>(productAndImageInfos);
    }

    public String getUpdatedDescription(String description, String editorFirebaseImageUrl, String blobUrl) {
        String patternString = "<img([^>]*)src=[\"']" + Pattern.quote(blobUrl) + "[\"']([^>]*)>";
        Pattern pattern = Pattern.compile(patternString);
        String updatedDiscription = pattern.matcher(description).replaceAll(String.format("<img src=\"%s\"$1$2", editorFirebaseImageUrl));
        return updatedDiscription;
    }

    @CacheEvict(cacheNames = {"totalProduct", "onSaleProduct", "offSaleProduct", "soldOutProduct"}, allEntries = true, beforeInvocation = true)
    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @CacheEvict(cacheNames = {"totalProduct", "onSaleProduct", "offSaleProduct", "soldOutProduct"}, allEntries = true, beforeInvocation = true)
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public Product selectProductAllById(Long id) {
        return productRepository.selectProductAllById(id);
    }

    @Cacheable(cacheNames = "counts", key = "'admin'", cacheManager = "cacheManager")
    public CountOfProductsResDto countOfProductsByStatus() {
        return productRepository.countOfProductsByStatus();
    }

    @CacheEvict(cacheNames = "counts",  key = "'admin'", beforeInvocation = true, cacheManager = "cacheManager")
    public void updateProductStatus(Long productId, String status) {
        productRepository.updateProductStatus(productId, status);
    }
}
