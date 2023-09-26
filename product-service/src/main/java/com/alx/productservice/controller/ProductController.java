package com.alx.productservice.controller;

import com.alx.productservice.dto.ProductRequestDto;
import com.alx.productservice.dto.ProductResponseDto;
import com.alx.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

}
