#include "iostream"
#include "cstring"

using namespace std;

/*
 *编写一个程序，要求用户以度、分、秒的方式输入一个维度，然后以度为单位显示该维度。1度等于60分，1分等于60秒，
 * 请以常量的方式表示这些值。对于每个输入值，应使用一个独立的变量储存它。
 * 下面是该程序运行时的情况：
 * Enter a alatitude in degrees, minutes, and seconds:
 * First, enter the degrees: 37
 * Next, enter the minutes of arc: 51
 * Finally, enter the seconds of arc: 19
 * 37 degrees, 51 minutes, 19 seconds = 37.8553 degrees
 */
const float MINUTS = 60;
const float SECONDS = 60;

int main3() {
    float degree = 0;
    float minutes = 0;
    float seconds = 0;

    cout << "Enter a alatitude in degrees, minutes, and seconds:" << endl;

    cout << "First, enter the degrees:";
    cin >> degree;

    cout << "Next, enter the minutes of arc:";
    cin >> minutes;

    cout << "Finally, enter the seconds of arc:";
    cin >> seconds;

    degree = degree + minutes/MINUTS + seconds/SECONDS/MINUTS;
    cout << degree << " degrees, " << minutes << " minutes, "<<
    seconds <<" seconds = "<< degree <<" degrees" << endl;

    return 0;
}