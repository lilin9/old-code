#include "iostream"

using namespace std;

/*
 * 编写一个C++程序，它要求用户输入一个以浪为单位的距离，然后将它转换为码（一浪等于220码）
 */
int main2() {
    int input = 0;
    cout << "输入一个距离，它将以浪为单位: ";
    cin >> input;
    cout << input << "浪等于" << 220*input << "码";
    return 0;
}