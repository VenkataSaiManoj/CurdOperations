package com.amazon.products.repository;

import com.amazon.products.entity.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Products, Integer> {
    //@Query("{'code':?0}")
    //Products findByItemId(String id);
}
