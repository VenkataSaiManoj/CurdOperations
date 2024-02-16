package com.amazon.products.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("products")
public class Products {
    public Products(int code, String name, String main_category, String sub_category, String image, Object myratings, Object myNo_of_ratings, String actaul_price, String discount_price) {
        this.code = code;
        this.name = name;
        this.main_category = main_category;
        this.sub_category = sub_category;
        this.image = image;
        this.myratings = myratings;
        this.myNo_of_ratings = myNo_of_ratings;
        this.actaul_price = actaul_price;
        this.discount_price = discount_price;
    }

    @Id

    int code;
    String name;
    String main_category;

    public Products() {
    }

    String sub_category;
    String image;
    @Field("ratings")
    Object myratings;
    @Field("no_of_ratings")
    Object myNo_of_ratings;
    String actaul_price;
    String discount_price;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getMyratings() {
        return myratings;
    }

    public void setMyratings(Object myratings) {
        this.myratings = myratings;
    }

    public Object getMyNo_of_ratings() {
        return myNo_of_ratings;
    }

    public void setMyNo_of_ratings(Object myNo_of_ratings) {
        this.myNo_of_ratings = myNo_of_ratings;
    }

    public String getActaul_price() {
        return actaul_price;
    }

    public void setActaul_price(String actaul_price) {
        this.actaul_price = actaul_price;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }
}
