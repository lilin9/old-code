package NO5_ObjectOriented_BigWork.Shopping;

import NO5_ObjectOriented_BigWork.Interfaces.*;

//计算所有商品一共需要的价钱
public class Money implements vip_Settlement,common_Settlement {
    //普通会员的计算方法
    @Override
    public double common_get_Price(double price) {
        return price;
    }

    //VIP会员的计算方法：年度VIP会员购买商品可以打9.5折
    @Override
    public double vip_get_Price(double price) {
        return 0.95*price;
    }
}
