package org.soft.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply implements Serializable {
    private int replyId;
    private String replyContent;
    private String replyTime;
    private int humanId;
    private int articleId;

    private Human human;
}
