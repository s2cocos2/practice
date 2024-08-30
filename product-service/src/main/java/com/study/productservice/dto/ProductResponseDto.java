package com.study.productservice.dto;

import com.study.productservice.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private Long price;
    private Long stock;

    public ProductResponseDto(Product product) {
        this.id = product.getProductId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.stock = product.getStock();


//    private List<FolderResponseDto> productFolderList = new ArrayList<>();

//    public ProductResponseDto(Product product) {
//        this.id = product.getId();
//        this.title = product.getTitle();
//        this.link = product.getLink();
//        this.image = product.getImage();
//        this.lprice = product.getLprice();
//        this.myprice = product.getMyprice();
//        for (ProductFolder productFolder : product.getProductFolderList()) {
//            productFolderList.add(new FolderResponseDto(productFolder.getFolder()));

//        }
    }
}
