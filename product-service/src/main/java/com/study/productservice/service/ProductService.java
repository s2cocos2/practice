package com.study.productservice.service;

import com.study.productservice.dto.ProductRequestDto;
import com.study.productservice.dto.ProductResponseDto;
import com.study.productservice.entity.Product;
import com.study.productservice.exception.CommonException;
import com.study.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.study.productservice.exception.errorcode.ClientErrorCode.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
//    private final FolderRepository folderRepository;
//    private final ProductFolderRepository productFolderRepository;
//    private final MessageSource messageSource;

    //상품 등록
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
//        Product product = productRepository.save(new Product(requestDto));
//        return new ProductResponseDto(product);
        return null;
    }

    // 상품 전체 조회
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProducts(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);


        Page<Product> productList;

        productList = productRepository.findAll(pageable);

        return productList.map(ProductResponseDto::new);

    }

//    // 상품 전체 조회
//    @Transactional(readOnly = true)
//    public Page<ProductResponseDto> getProducts(User user, int page, int size, String sortBy, boolean isAsc) {
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        UserRoleEnum userRoleEnum = user.getRole();
//
//        Page<Product> productList;
//
//        if(userRoleEnum == UserRoleEnum.USER){
//            productList = productRepository.findAllByUser(user, pageable);
//        } else {
//            productList = productRepository.findAll(pageable);
//        }
//        return productList.map(ProductResponseDto::new);
//
//    }

    //상품 상세 조회
    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new CommonException(NO_PRODUCT));

        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return productResponseDto;
    }

//    @Transactional
//    public ProductResponseDto updateProduct(Long id, ProductMypriceRequestDto requestDto) {
//        int myprice = requestDto.getMyprice();
//        if(myprice < MIN_MY_PRICE){
//            throw new IllegalArgumentException(
//                    messageSource.getMessage(
//                            "below.min.my.price",
//                            new Integer[]{MIN_MY_PRICE},
//                            "Wrong Price",
//                            Locale.getDefault()
//
//                    )
//            );
//        }
//        Product product = productRepository.findById(id).orElseThrow(
//                ()-> new ProductNotFoundException(messageSource.getMessage(
//                        "not.found.product",
//                        null,
//                        "Not Found Product",
//                        Locale.getDefault()
//
//                )));
//        product.update(requestDto);
//
//        return new ProductResponseDto(product);
//    }
//
//
//
//    @Transactional
//    public void updateBySearch(Long id, ItemDto itemDto) {
//        Product product = productRepository.findById(id).orElseThrow(
//                ()-> new NullPointerException("해당 상품은 존재하지 않습니다."));
//        product.updateByItemDto(itemDto);
//    }
//
//    public void addFolder(Long productId, Long folderId, User user) {
//        Product product = productRepository.findById(productId).orElseThrow(
//                ()-> new NullPointerException("해당 상품이 존재하지 않습니다."));
//
//        Folder folder = folderRepository.findById(folderId).orElseThrow(
//                ()-> new NullPointerException("해당 폴더가 존재하지 않습니다."));
//
//        if(!product.getUser().getId().equals(user.getId())
//                || !folder.getUser().getId().equals(user.getId())){
//            throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다.");
//        }
//
//        Optional<ProductFolder> overlapFolder =  productFolderRepository.findByProductAndFolder(product, folder);
//
//        if(overlapFolder.isPresent()){
//            throw new IllegalArgumentException("중복된 폴더입니다.");
//        }
//
//        productFolderRepository.save(new ProductFolder(product, folder));
//    }
//
//    public Page<ProductResponseDto> getProductsInFolder(Long folderId, int page, int size, String sortBy, boolean isAsc, User user) {
//
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//
//        Page<Product> productList = productRepository.findAllByUserAndProductFolderList_FolderId(user, folderId, pageable);
//
//        Page<ProductResponseDto> responseDtoList = productList.map(ProductResponseDto::new);
//        return responseDtoList;
//    }
}

