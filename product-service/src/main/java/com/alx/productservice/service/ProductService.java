package com.alx.productservice.service;

import com.alx.productservice.dto.ProductRequestDto;
import com.alx.productservice.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    void create(ProductRequestDto productRequestDto);

    List<ProductResponseDto> findAll();
}
