package com.alx.productservice.service;

import com.alx.productservice.dto.ProductRequestDto;
import com.alx.productservice.dto.ProductResponseDto;
import com.alx.productservice.model.Product;
import com.alx.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional
    public void create(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product '{}' is saved", product.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductResponseDto)
                .toList();
    }


    private ProductResponseDto mapToProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
