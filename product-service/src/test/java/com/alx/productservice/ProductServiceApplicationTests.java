package com.alx.productservice;

import com.alx.productservice.dto.ProductRequestDto;
import com.alx.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers  // xq vamos a W con Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0"); // mongo container name + version


    @Autowired
    private MockMvc mockMvc;   // to call endpoints  <--  requires  @AutoConfigureMockMvc

    @Autowired
    private ObjectMapper objectMapper;  // map POJO Object to JSON and JSON to POJO Object

    @Autowired
    private ProductRepository productRepository;


    // // Load properties dynamically
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }


    // // Test that call an Endpoint: moc mvc to call controller endpoints
    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequestDto productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);  // may throw exception

        // create product
        mockMvc.perform( // may throw exception
                MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString)
        ).andExpect(status().isCreated());


        // check if it actually has been created
        Assertions.assertEquals(1, productRepository.findAll().size());
    }


    private ProductRequestDto getProductRequest() {
        return ProductRequestDto.builder()
                .name("Readme Note 15 Pro")
                .description("Some Awesome Description")
                .price(BigDecimal.valueOf(450))
                .build();
    }

}
