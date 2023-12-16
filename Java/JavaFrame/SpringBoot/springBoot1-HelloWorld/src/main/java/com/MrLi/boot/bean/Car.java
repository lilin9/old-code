package com.MrLi.boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by MrLi on 2022/03/16/14:13
 */

/*
只有在容器中的组件，才能使用SpringBoot提供的强大功能
 */
//@Component//声明其为容器中的组件
@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private String price;

    public Car() {
    }

    public Car(String brand, String price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
