package com.alx.productservice.controller;

import com.alx.productservice.dto.ProductRequestDto;
import com.alx.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ProductRequestDto productRequestDto) {
        productService.create(productRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
