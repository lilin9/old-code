#include "iostream"

using namespace std;
/*
 * 编写一个要求用户输入数字的程序。每次输入后，程序都将报告到目前为止，所有输入的累计和。
 * 当用户输入 0 时，程序结束。
 */
int main2() {
    int num = 0;
    int sum = 0;

    while (1) {
        cout << "\nEnter a number(don't enter 0): ";
        cin >> num;

        if (num == 0) {
            cout << "by!" << endl;
            break;
        }

        sum += num;

        cout << "Now, sum of your enter is: " << sum;
    }
    return 0;
}