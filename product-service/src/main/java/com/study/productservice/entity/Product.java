package com.study.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long stock;

//    @OneToMany(mappedBy = "product")
//    private List<ProductFolder> productFolderList = new ArrayList<>();

//    public Product(ProductRequestDto requestDto, User user) {
//        this.title = requestDto.getTitle();
//        this.price = requestDto.getPrice();
//        this.stock = requestDto.getStock();
//    }

    public void reStock(Long stock) {
        this.stock = stock;
    }
}