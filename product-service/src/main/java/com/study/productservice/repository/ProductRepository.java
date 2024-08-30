package com.study.productservice.repository;

import com.study.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    Page<Product> findAllByUser(User user, Pageable pageable);

//    Page<Product> findAllByUserAndProductFolderList_FolderId(User user, Long folderId, Pageable pageable);
}

