#include "iostream"

using namespace std;
/*
 * 假设要销售 C++ For Fools 一书。请编写一个程序，输入全年中每个月的销售量（图书数量，而不是销售额）。
 * 程序通过循环，使用初始化为月份字符串 的 char *数组（或 string 对象数组）逐月进行提示，并将输入的
 * 数据储存在一个 int 数组中。然后，程序计算数组中各元素的总数，并报告这一年的销售情况。
 */

int main4() {
    string month[12];
    int sales[12];
    int number = 0;
    for (int i = 0; i < 12; ++i) {
        month[i] = to_string(i+1) + "月份";
        cout << "《C++ For Fools》" << month[i] << "的销售量是：";
        cin >> number;
        sales[i] = number;
    }

    int sum = 0;
    for (int i = 0; i < 12; ++i) {
        sum += sales[i];
    }

    cout << "《C++ For Fools》" << month[0] << "到" << month[11] << "的销售总量是" << sum << "本" << endl;

    return 0;
}