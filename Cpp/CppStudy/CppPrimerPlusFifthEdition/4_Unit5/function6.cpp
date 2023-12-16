#include "iostream"

using namespace std;
/*
 * 设计一个名为 car 的结构，用它储存下述有关汽车的信息：生厂商（储存在字节数组或 string 对象中的字符串）、
 * 生产年份（整数）。编写一个程序，向用户询问有多少辆汽车。随后，程序使用 new 来创建一个由相应数量的 car
 * 结构组成的动态数组。接下来，程序提示用户输入每辆车的生厂商（可能由多个单词组成）和年份信息。请注意，
 * 这需要特别小心，因为它将交替读取数值和字符串。最后，程序将显示每个结构的内容。该程序运行情况如下：
 * How many cars do you wish to catalog? 2
 * Car #1:
 * Please enter the make: Hudson Hornet
 * Please enter the year made: 1952
 * Car #2:
 * Please enter make: Kaiser
 * Please enter the year made: 1951
 * Here is your collection:
 * 1952 Hudson Hornet
 * 1951 Kaiser
 */

struct Car {
    string manufacturer;
    int yearMade;
};

int main6() {
    int number = 0;
    cout << "How many cars do you wish to catalog?";
    cin >> number;

    Car *carArray = new Car[number];
    for (int i = 0; i < number; ++i) {
        Car car;

        cout << "Car #" << i+1 << endl;
        cout << "Please enter make: ";
        cin.ignore();
        getline(cin, car.manufacturer);
        cout << "Please enter the year made: ";
        cin >> car.yearMade;

        carArray[i] = car;
    }

    cout << "Here is your collection:" << endl;
    for (int i = 0; i < number; ++i) {
        cout << carArray[i].yearMade << " " << carArray[i].manufacturer << endl;
    }
    return 0;
}