#include <iostream>
#include "string.h"
using namespace std;

/*
    Betelgeusean plorg有这些特征：
    数据：
    - plorg的名称不超过19个字符
    - plorg有满意指数（CI），这是一个整数。
    操作：
    - 新的plorg将有名称，其CI值为50
    - plorg的CI可以修改
    - plorg可以报告它的名称和CI
    - plorg的默认名称为 "Plorga"。
    请编写一个Plorg类声明（包括数据成员和成员函数原型）来表示plorg，并编写成员函数的函数定义。
    然后编写一个小程序，以演示Plorg类的所有特性。
*/

class Plorg {
    private:
    char name[19];
    int ci;

    public:
    Plorg() {
        strcpy(this->name, "PLorga");
        this->ci = 50;
    }

    Plorg(char *name) {
        strcpy(this->name, name);
        this->ci = 50;
    }

    void setCI(int ci) {
        this->ci = ci;
    }

    void showPlorg() {
        cout << "Name: " << this->name << ", CI: " << this->ci << endl;
    }
};

int main7() {
    Plorg p1;
    p1.showPlorg();

    Plorg p2("bbcc");
    p2.showPlorg();

    p1.setCI(100);
    p1.showPlorg();
    return 0;
}
