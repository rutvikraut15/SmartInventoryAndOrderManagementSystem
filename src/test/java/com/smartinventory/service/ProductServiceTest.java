package com.smartinventory.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.smartinventory.entity.Product;
import com.smartinventory.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void testFindProduct() {

        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Dell Laptop");

        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Product result = service.findProduct(1L);

        assertEquals(1L, result.getProductId());
        assertEquals("Dell Laptop", result.getProductName());
    }
}