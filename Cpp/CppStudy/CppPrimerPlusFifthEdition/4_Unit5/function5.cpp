#include "iostream"

using namespace std;
/*
 * 完成编程练习 function4.cpp，但这一次使用一个二维数组来储存输入——3年中每个月的销售量。程序将报告
 * 每年的销售量以及3年的总销售量。
 */

int main5() {
    int sales[3][12];

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 12; ++j) {
            sales[i][j] = i * j + 10;
        }
    }

    int sumSalesForMonth = 0;
    int sumSalesForYear = 0;
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 12; ++j) {
            sumSalesForMonth += sales[i][j];
            sumSalesForYear += sales[i][j];
        }
        cout << "图书第" << i << "年的销售量是：" << sumSalesForMonth << endl;
        sumSalesForMonth = 0;
    }
    cout << "图书三年的总销售量是：" << sumSalesForYear << endl;
    return 0;
}