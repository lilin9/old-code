#include "iostream"

using namespace std;
/*
 * 编写一个要求用户输入两个整数的程序。该程序将计算并输出这两个整数之间（包括这两个整数）
 * 所有整数的和。这里假设先输入较小的整数。例如，如果用户输入的 2 和 9，则程序将指出 2~9 之间
 * 所有整数的和为 44
 */
int main1() {
    int start = 0;
    int end = 0;
    int sum = 0;

    cout << "Enter start number: ";
    cin >> start;
    cout << "Enter end number: ";
    cin >> end;

    for (int i = start; i <= end; ++i) {
        sum += i;
    }

    cout << "sum of " << start << " to " << end << " is " << sum;
    return 0;
}