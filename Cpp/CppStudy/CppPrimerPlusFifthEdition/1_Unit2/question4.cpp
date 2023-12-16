#include "iostream"

using namespace std;

/*
 * 编写一个程序，其中的 main() 调用一个用户定义的函数（以摄氏温度值为参数，并返回相应的华氏温度值）。
 * 该程序按照下面的格式要求用户输入摄氏温度值，并显示结果：
 * Please enter a Celsius value: 20
 * 20 degrees Celsius is 68 degrees Fahrenheit.
 * 下面是转换公式：
 * 华氏温度 = 1.8 ×摄氏温度 + 32.0
 */

float getFa(float cel) {
    return 1.8*cel+32;
}

int main4() {
    float cel = 0;

    cout << "Please enter a Celsius value:";
    cin >> cel;
    cout << cel << " degrees Celsius is " << getFa(cel) <<" degrees Fahrenheit" << endl;
    return 0;
}