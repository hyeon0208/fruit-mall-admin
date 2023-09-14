package com.fruit.mall_admin.product;

import com.fruit.mall_admin.product.dto.CountOfProductsResDto;
import com.fruit.mall_admin.product.dto.ProductAndImageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ProductMapper {
    private final ProductMapper productMapper;

    @Override
    public void insertProduct(Product product) {
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void updateProductStock(Long id, int orderCount) {
        productMapper.updateProductStock(id, orderCount);
    }

    @Override
    public int selectProductStock(Long id) {
        return productMapper.selectProductStock(id);
    }

    @Override
    public void deleteProductById(Long id) {
        productMapper.deleteProductById(id);
    }

    @Override
    public Product selectProductAllById(Long id) {
        return productMapper.selectProductAllById(id);
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    @Override
    public List<Product> selectAllByFilter(String status, String category, String searchCond) {
        return productMapper.selectAllByFilter(status, category, searchCond);
    }

    @Override
    public List<ProductAndImageInfo> selectProductAndImageByFilter(String category, String searchCond, Long id) {
        return productMapper.selectProductAndImageByFilter(category, searchCond, id);
    }

    @Override
    public CountOfProductsResDto countOfProductsByStatus() {
        return productMapper.countOfProductsByStatus();
    }

    @Override
    public void updateProductStatus(Long productId, String status) {
        productMapper.updateProductStatus(productId, status);
    }
}
