#include "iostream"

using namespace std;

/*
 * 编写通接受一个参数（字符串的地址），并打印该字符串的函数。不过，如果提供了第二个参数（int类型），
 * 且该参数不为0，则该函数打印字符串的次数将为该函数被调用的次数（注意，字符串的打印次数不等于第二个
 * 参数的值，而等于函数被调用的次数）。是的，这是一个非常可笑的函数，但它能让读者能够使用本章介绍的
 * 一些技术。在一个简单的程序中使用该函数，以演示该函数是如何工作的。
 */

//记录 print() 函数被调用几次
int count = 0;

void print(string &str, int num = 0) {
    count ++;
    if (num != 0) {
        for (int i = 0; i < count; ++i) {
            cout << str << endl;
        }
        cout << endl;
    } else {
        cout << str << endl;
    }

}

int main1() {
    string str = "Hello World";
    print(str, 1);
    print(str, 1);
    print(str, 1);
    print(str, 1);
    return 0;
}