#include "iostream"

using namespace std;

/*
 * 定义一个递归函数，接受一个整数参数，并返回该参数的乘阶。前面讲过，3 的乘阶写作 3!，等于 3*2!，
 * 以此类推，而 0! 被定义为1。通用的计算公式是，如果n大于零，则 n!=n*(n-1)!。在程序中对该函数进行
 * 测试，程序使用循环让用户输入不同的值，程序将报告这些值的乘阶。
 */

double factorial(double number) {
    if (number == 0) {
        return 1.0;
    }
    if (number < 0) {
        return 0;
    }
    return number * factorial(number - 1);
}

int main5() {
    double number = 0;
    cout << "Enter one number greater than 0: ";
    while (cin >> number && number >= 0) {
        cout << "number! = " << factorial(number) << endl;
        cout << "Enter one number greater than 0: ";
    }
    return 0;
}