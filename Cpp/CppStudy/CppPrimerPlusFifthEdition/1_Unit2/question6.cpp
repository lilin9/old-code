#include "iostream"

using namespace std;

/*
 * 编写一个程序，要求用户输入小时数和分钟数。在 main() 函数中，将这两个值传递给一个 void 函数，
 * 后者以下面这样的格式显示这两个值：
 * Enter the number of hours: 9
 * Enter the number of minutes: 28
 * Time: 9:28
 */

void showTime(int hours, int minutes) {
    cout << "Time: " << hours << ":" << minutes << endl;
}

int main6() {
    int hours = 0;
    int minutes = 0;

    cout << "Enter the number of hours: ";
    cin >> hours;
    cout << "Enter the number of minutes: ";
    cin >> minutes;

    showTime(hours, minutes);
    return 0;
}