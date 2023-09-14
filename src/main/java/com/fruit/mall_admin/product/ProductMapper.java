package com.fruit.mall_admin.product;

import com.fruit.mall_admin.product.dto.CountOfProductsResDto;
import com.fruit.mall_admin.product.dto.ProductResDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    void insertProduct(Product product);

    void updateProduct(Product product);

    void updateProductStock(@Param("productId") Long id, @Param("orderCount") int orderCount);

    int selectProductStock(@Param("productId") Long id);

    void deleteProductById(@Param("productId") Long id);

    Product selectProductAllById(@Param("productId") Long id);

    List<ProductResDto> selectAll();

    List<ProductResDto> selectAllByFilter(@Param("status") String status, @Param("category") String category, @Param("searchCond") String searchCond);

    CountOfProductsResDto countOfProductsByStatus();

    void updateProductStatus(@Param("productId") Long productId, @Param("status") String status);
}
