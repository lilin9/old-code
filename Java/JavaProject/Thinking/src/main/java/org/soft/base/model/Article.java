package org.soft.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    private int articleId;
    private String articleTitle;
    private String articleContent;
    private Date articleIssueTime;
    private int humanId;
    private Human human;
    private int typeId;
    private Type type;
    private String result;

}
