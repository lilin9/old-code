#include "iostream"

using namespace std;
/*
 * 编写一个程序，要求用户以整数方式输入秒数（使用 long 变量储存），然后以天、小时、分钟和秒的方式
 * 显示这段时间。使用符号常量来表示每天有多少小时、每小时有多少分钟以及每分钟有多少秒。该程序
 * 的输出应与下面类似：
 * Enter the number of seconds: 31600000
 * 31600000 seconds = 360 days, 46minutes, 40seconds
 */
int main4() {
    long seconds = 0;
    cout << "Enter the number of seconds:";
    cin >> seconds;

    long days = seconds / 8640;
    long minutes = seconds % 8640 / 60;
    seconds = seconds - days*8640 - minutes*60;

    cout << seconds << "seconds = "<< days <<" days, "<< minutes << "minutes, " << seconds << "seconds" << endl;
    return 0;
}