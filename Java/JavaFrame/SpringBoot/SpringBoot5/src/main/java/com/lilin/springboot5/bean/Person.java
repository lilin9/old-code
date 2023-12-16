package com.lilin.springboot5.bean;

import lombok.Data;

/**
 * Created by LiLin on 2022/03/30/14:21
 */
@Data
public interface Person {
    String getName();
    Integer getAge();
}
