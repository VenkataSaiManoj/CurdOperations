package com.amazon.products.controller;

import com.amazon.products.entity.Products;
import com.amazon.products.service.ImportCSVDataService;
import com.amazon.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@RestController

public class ImportCSVDataController {
    @Autowired
    ImportCSVDataService csvDataService;
    @Autowired
    ProductService netflixService;

    List<Products> netflixDataList = new ArrayList<>();

    @RequestMapping(value = "/importDataFromCsvFile" , method = RequestMethod.POST , consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String importDataFromCSVFile(@RequestParam("csvFile") MultipartFile file) throws Exception {
        try {
            //public static File createTempFile(String PREFIX, String SUFFIX)
            File tempFile = File.createTempFile("temp",null); //I created this temp file bcz, when i am calling the importNetflixDataFromCSV() method i need to pass the location of the file that i want to import, when i passed the file directly i got an error since i passed the entire file.

            file.transferTo(tempFile);
            netflixDataList = csvDataService.importNetflixDataFromCSV(tempFile.getAbsolutePath());
//			for (Products eachrow : netflixDataList)
//				netflixService.saveNetflixData(eachrow);   // takes more time, since each row is being saved individually.



        } catch (Exception e){
            System.out.println(e);
        }
        if(!netflixDataList.isEmpty())
            return "Data successfully inserted into DB!!!";
        else
            return null;
    }

    @RequestMapping(value = "/sample" , method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity samplemethod(@RequestParam String name)
    {
        return new ResponseEntity(name, HttpStatus.OK);
    }
}
