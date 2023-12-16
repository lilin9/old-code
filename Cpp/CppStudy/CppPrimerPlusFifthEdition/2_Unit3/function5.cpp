#include "iostream"

using namespace std;
/*
 * 编写一个程序，要求用户输入驱车里程（英里）和使用汽油量（加仑），然后指出汽车耗油量为一加仑
 * 的里程。如果愿意，也可以让程序要求用户以公里为单位输入距离，并以升为单位输入汽油量，然后指出
 * 欧洲风格的结果——即每100公里的耗油量（升）。
 */
int main5() {
    float mile = 0;
    float lift = 0;

    cout << "Enter driving mileage: ";
    cin >> mile;
    cout << "Enter Fuel consumption: ";
    cin >> lift;

    cout << "Car burns 1 gallon of gas and travels "<< mile/lift <<" kilometers";
    return 0;
}