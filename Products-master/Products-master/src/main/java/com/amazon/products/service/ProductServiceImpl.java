package com.amazon.products.service;

import com.amazon.products.entity.Products;
import com.amazon.products.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepo repo;
    @Override
    public List<Products> getProducts() {
        return repo.findAll();
    }

    @Override
    public Optional<Products> findByItemId(int id) {
        Optional<Products> response = repo.findById(id);
        return  response;
    }

    @Override
    public void saveAllRecordsInCsvFileAtOnce(List<Products> netflixDataList) {
        repo.saveAll(netflixDataList);
    }

    @Override
    public Products saveNetflixData(Products newdata) {
        return repo.save(newdata);
    }

    @Override
    public void deleteByCode(int code)
    {
        repo.deleteById(code);
    }

    @Override
    public Products updateProduct(Products new_product ,int code)
    {
        Optional<Products> optionalOldProduct = repo.findById(code);
        if(!optionalOldProduct.isEmpty() )
        {
            Products oldProduct = optionalOldProduct.get();
            oldProduct.setName(new_product.getName());
            oldProduct.setMyratings(new_product.getMyratings());
            oldProduct.setMyNo_of_ratings(new_product.getMyNo_of_ratings());
            oldProduct.setActaul_price(new_product.getActaul_price());
            oldProduct.setDiscount_price(new_product.getDiscount_price());
            repo.save(oldProduct);
            return oldProduct;
        }
        else
            return null;
    }
}
