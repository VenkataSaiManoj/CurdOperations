package com.amazon.products.service;

import com.amazon.products.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Products> getProducts();

    Optional<Products> findByItemId(int id);

    void saveAllRecordsInCsvFileAtOnce(List<Products> netflixDataList);

    Products saveNetflixData(Products newdata);

    void deleteByCode(int code);

    Products updateProduct(Products newProduct , int code);
}
