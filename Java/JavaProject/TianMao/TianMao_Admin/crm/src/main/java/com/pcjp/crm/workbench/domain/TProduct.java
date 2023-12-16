package com.pcjp.crm.workbench.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * (TProduct)实体类
 *
 * @author makejava
 * @since 2022-06-02 10:41:46
 */
public class TProduct implements Serializable {
    private static final long serialVersionUID = -28940367277395280L;
    /**
     * 商品id
     */
    private Integer id;
    /**
     * 分类id
     */
    private Integer categoryId;
    /**
     * 商品系列
     */
    private String itemType;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品卖点
     */
    private String sellPoint;
    /**
     * 商品单价
     */
    private Long price;
    /**
     * 库存数量
     */
    private Integer num;
    /**
     * 图片路径
     */
    private String image;
    /**
     * 商品状态  1：上架   2：下架   3：删除
     */
    private Integer status;
    /**
     * 显示优先级
     */
    private Integer priority;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 最后修改时间
     */
    private Date modifiedTime;
    /**
     * 创建人
     */
    private String createdUser;
    /**
     * 最后修改人
     */
    private String modifiedUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

}

