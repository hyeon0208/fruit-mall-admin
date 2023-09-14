package com.fruit.mall_admin.product;

import com.fruit.mall_admin.category.CategoryService;
import com.fruit.mall_admin.firebase.FireBaseService;
import com.fruit.mall_admin.firebase.UploadResult;
import com.fruit.mall_admin.image.Image;
import com.fruit.mall_admin.image.ImageService;
import com.fruit.mall_admin.image.dto.FileInfo;
import com.fruit.mall_admin.product.dto.ProductRegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ImageService imageService;
    private final FireBaseService fireBaseService;
    private final CategoryService categoryService;

    private final static String PATH = "images";
    private final static String ON_SALE = "판매중";

    @PostMapping("/add/product")
    @ResponseBody
    public String addProduct(@ModelAttribute ProductRegistrationForm form,
                             @RequestParam("images") List<MultipartFile> images,
                             @RequestParam(value = "imageUrls", required = false) List<String> imageUrls) throws IOException {

        List<FileInfo> imageInfo = new ArrayList<>();
        MultipartFile productImage = images.get(0);
        images.remove(0);
        String productFileName = productImage.getOriginalFilename();
        UploadResult productImgResult = fireBaseService.uploadFiles(productImage, PATH, productFileName);
        imageInfo.add(new FileInfo(productImgResult.getFirebaseImageUrl(), productImgResult.getFileName()));

        String description = form.getDescription();
        for (int i = 0; i < images.size(); i++) {
            MultipartFile file = images.get(i);
            String editorFileName = images.get(i).getOriginalFilename();
            UploadResult editorImgResult = fireBaseService.uploadFiles(file, PATH, editorFileName);

            // editorFirebaseImageUrl blobUrl을 비교해 같으면 해당 상세내용의 src을 editorFirebaseImageUrl로 변경
            String editorFirebaseImageUrl = editorImgResult.getFirebaseImageUrl();
            String blobUrl = imageUrls.get(i);
            description = productService.getUpdatedDescription(description, editorFirebaseImageUrl, blobUrl);

            imageInfo.add(new FileInfo(editorFirebaseImageUrl, editorImgResult.getFileName()));
        }

        Long categoryId = categoryService.selectIdByCategoryName(form.getSort());
        Product product = Product.builder()
                .categoryId(categoryId)
                .productName(form.getProductName())
                .productPrice(form.getPrice())
                .productStock(form.getStock())
                .productDescription(description)
                .productDiscount(form.getDiscount())
                .productSaleStatus(ON_SALE)
                .build();
        productService.insertProduct(product);

        for (int i = 0; i < imageInfo.size(); i++) {
            Image image = Image.builder()
                    .productId(product.getProductId())
                    .imageUrl(imageInfo.get(i).getFirebaseImageUrl())
                    .path(PATH)
                    .fileName(imageInfo.get(i).getFileName())
                    .build();
            imageService.insertImage(image);
        }

        return "success";
    }
}
