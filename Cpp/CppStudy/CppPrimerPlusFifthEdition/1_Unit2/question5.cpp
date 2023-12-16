#include "iostream"

using namespace std;

/*
 *编写一个程序，其 main() 调用一个用户定义的函数（以光年值为参数，并返回对应天文单位的值）。
 * 该程序按下面的格式要求用户输入光年值，并显示结果：
 * Enter the number of light years: 4.2
 * 4.2 light years = 265608 astronomical units.
 * 天文单位是从地球到太阳的平均距离（约 150000000 千米或 93000000 英里），光年是光一年走的距离（约 10 万亿千米或 6 万亿英里。除太阳外，
 * 最近的恒星大约离地球 4.2 光年）。请使用 double 类型，转换公式为：
 * 1 光年 = 63240 天文单位
 */

double compute(double light) {
    return 63240 * light;
}

int main5() {
    double light = 0;
    cout << "Enter the number of light years: ";
    cin >> light;
    cout << light << " light years = "<< compute(light) <<" astronomical units.";

    return 0;
}