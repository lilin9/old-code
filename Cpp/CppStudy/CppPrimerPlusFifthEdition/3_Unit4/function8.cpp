#include "iostream"

using namespace std;
/*
 * 完成编程练习 function7.cpp，但使用 new 来为结构分配内存，而不是声明一个结构变量。另外，
 * 让程序在请求输入披萨饼公司名称之前输入披萨饼的直接。
 */
struct Pizza {
    string companyName;
    float dia;
    float weight;
};

int main8() {
    auto *pizza = new Pizza();

    cout << "Enter weight of Pizza: ";
    cin >> pizza->weight;

    //因为是先输入重量，再输入公司名字，所以当输入重量时，回车键会被 getline() 捕获
    //导致 getline(cin, pizza->companyName); 没有被执行，而加上 cin.ignore(); 能让程序忽略
    // 输入重量后的回车键
    cin.ignore();

    cout << "Enter name of Pizza company: ";
    getline(cin, pizza->companyName);
    cout << "Enter diameter of Pizza: ";
    cin >> pizza->dia;

    cout << "According your input, the name of the pizza company is [" << pizza->companyName
         << "] , \nits diameter is [" << pizza->dia << "]cm and weight is [" << pizza->weight << "]kg.";

    delete pizza;
    return 0;
}