package com.MrLi.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by MrLi on 2022/03/26/14:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String bookname;
    private BigDecimal price;
    private String author;
    private Integer sales;
    private Integer stock;
    private String imgPath;
}
