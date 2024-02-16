package com.amazon.products.controller;

import com.amazon.products.entity.Products;
import com.amazon.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductService service;

    // insert new product
    @RequestMapping(value = "/products" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Products> insertProducts(@RequestBody Products data) {
        Products newData = service.saveNetflixData(data);
        if(newData != null)
            return new ResponseEntity<>(data, HttpStatus.OK);
        else
            throw new RuntimeException("Inserting new data into database failed!!!");
    }

    // Get all products
    @RequestMapping(value = "/products" , method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Products>> getAllProducts()
    {
        List<Products> allproducts = service.getProducts();
        return new ResponseEntity<>(allproducts , HttpStatus.OK);
    }

    // Display the product’s detail using code.
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Products>> findByItemId(@PathVariable int id)
    {
        Optional<Products> product = service.findByItemId(id);
        return new ResponseEntity<>(product , HttpStatus.FOUND);
    }

    //Delete product by code
    @RequestMapping(value = "/products/{id}" , method = RequestMethod.DELETE)
    public String deleteBycode(@PathVariable int id)
    {
        Optional<Products> product = service.findByItemId(id);
        if(!product.isEmpty()) {
            service.deleteByCode(id);
            return ("Product id:" + id + " successfully deleted from DB");
        }
        return "Unable to delete from DB , becaue there is no product with the respective code";
    }

    @RequestMapping(value="/products/{code}" , method = RequestMethod.PATCH , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Products> updateProduct(@PathVariable int code , @RequestBody Products product1)
    {

            Products updatedProduct = service.updateProduct(product1 , code);
            if(updatedProduct != null )
            return new ResponseEntity<>(updatedProduct , HttpStatus.OK);
            else
                return null;
    }

}
