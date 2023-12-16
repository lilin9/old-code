package org.soft.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Human implements Serializable {

    private int humanId;
    private String humanName;
    private String humanPassword;
    private String humanEmail;
    private String humanNiceName;
    private String humanDescribe;

}
