#include "iostream"
#include "cctype"
#include "strings.h"
#include "cctype"

using namespace std;

/*
 * 编写一个程序，最多将 10 个 donation 值读入到一个 double 数组中。程序遇到非数字输入时将
 * 结束输入，并报告这些数字的平均值以及数组中有多少个数字大于平均值。
 */
int main2() {    //isdigit
    double input[10];
    int length = 0;
    double sum = 0;
    char temp;

    for (int i = 0; i < 10; ++i) {
        cout << "Enter a donation value: ";
        cin >> temp;
        cin.ignore();

        if (!isdigit(temp)) {
            cout << "bye!" << endl;
            break;
        }

        input[i] = (int) temp - 48;
        sum += input[i];
        length++;
    }

    double mean = sum / length;

    int count = 0;
    for (auto ite: input) {
        if (ite > mean) ++count;
    }

    cout << "Average of Arrays is: " << mean << "\nThere are " << count << " numbers greater the average" << endl;

    return 0;
}