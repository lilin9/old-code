#include "iostream"

using namespace std;
/*
 * 编写一个小程序，要求用户使用一个整数指出自己的身高（单位为英寸），然后将身高转换为英尺和英寸。
 * 该程序使用下划线字符来指示输入位置。另外，使用一个 const 符号常量来表示转换因子。
 */

const float FACTOR = 12;
int main1() {
    float height = 0;
    cout << "What is you height in inches: ";
    cin >> height;
    cout << "your height is " << height / FACTOR << " foot.";

    return 0;
}