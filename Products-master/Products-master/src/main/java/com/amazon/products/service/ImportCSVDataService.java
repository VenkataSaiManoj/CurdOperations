package com.amazon.products.service;

import com.amazon.products.entity.Products;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazon.products.repository.ProductRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportCSVDataService {

        @Autowired
        ProductRepo repo;
        public List<Products> importNetflixDataFromCSV(String csvFilePath) throws Exception {
            List<Products> products = new ArrayList<>();

            try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                for (CSVRecord csvRecord : csvParser) {
                    Products netflixData = new Products();
                    // Assuming column names are "code", "name", "main_category", "sub_category", "image",
                    // "ratings", "no_of_ratings", "actual_price", "discounted_price" in CSV
                    netflixData.setCode(Integer.parseInt(csvRecord.get("code")));
                    netflixData.setName(csvRecord.get("name"));
                    netflixData.setMain_category(csvRecord.get("main_category"));
                    netflixData.setSub_category(csvRecord.get("sub_category"));
                    netflixData.setImage(csvRecord.get("image"));
                    netflixData.setMyratings(csvRecord.get("ratings")); // You might need to parse this to appropriate type
                    netflixData.setMyNo_of_ratings(csvRecord.get("no_of_ratings")); // You might need to parse this to appropriate type
                    netflixData.setActaul_price(csvRecord.get("actual_price"));
                    netflixData.setDiscount_price(csvRecord.get("discount_price"));

                    // Add the product to the list
                    products.add(netflixData);
                }

                // Save all products to the database
                repo.saveAll(products);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return products;
        }
}
