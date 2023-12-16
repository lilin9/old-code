package NO2_Calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;

//编写一个日历程序

public class Application {
    public static void main(String[] args) {
        //new一个日期对象后，用当前的时间初始化
        var date = LocalDate.now();

        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1); //将date设置为这个月的第一天     minusDays(): 在当前的日期减去参数中的天数
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue(); //得到星期的数值，例如：星期一 --> 1    星期二 --> 2

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < value; i++) System.out.print("   "); //第一天星期几，value的值就是多少，首行便缩进多少
        while (date.getMonthValue() == month){
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) System.out.print("*");
            else System.out.print(" ");

            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) System.out.println();
        }
        if (date.getDayOfWeek().getValue() != 1) System.out.println();
    }
}
