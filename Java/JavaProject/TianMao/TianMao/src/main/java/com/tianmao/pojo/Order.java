package com.tianmao.pojo;

import com.tianmao.pojo.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by LiLin on 2022/05/16/11:13
 * <p>
 * 订单实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    private Integer oid;            //订单id
    private Integer uid;            //用户id
    private String recvName;        //收货人姓名
    private String recvPhone;       //收货人电话
    private String recvProvince;    //收货人所在省
    private String recvCity;        //收货人所在市
    private String recvArea;        //收货人所在区
    private String recvAddress;     //收货详细地址
    private Long totalPrice;        //总价
    private Integer status;         //状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成
    private Date orderTime;         //下单时间
    private Date payTime;           //支付时间
}
