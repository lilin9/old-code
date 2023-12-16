#include "iostream"

using namespace std;

/*
 * 编写一个程序，不断要求用户输入两个数，直到其中的一个为 0.对于每两个数，程序将使用一个
 * 函数来计算它们的调和平均数，并将结果返回给 main()，而后者将报告结果。调和平均数指的是
 * 倒数平均值的倒数，计算公式如下：
 * 调和平均数=2.0 * x * y / (x+y)
 */

int getHarmonicMean(int x, int y) {
    return 2 * x * y / (x+y);
}

int main1() {
    int x = 0;
    int y = 0;

    while (true) {
        cout << "Enter the number x: ";
        cin >> x;
        cout << "Enter the number y: ";
        cin >> y;

        if (x == 0 || y == 0) {
            break;
        }

        cout << "Harmonic Mean of x and y is " << getHarmonicMean(x, y) << endl;
    }
    return 0;
}