#pragma once
#include <cstring>
#include <iostream>

using namespace std;

//分文件声明模板类
template <class T1, class T2>
class Person {
   public:
    T1 name;
    T2 age;
    //声明函数
    Person(T1 name, T2 age);
    void showPerson();
};

template<class T1, class T2>
Person<T1, T2>::Person(T1 name, T2 age) {
    this->name = name;
    this->age = age;
}

template<class T1, class T2>
void Person<T1, T2>::showPerson() {
    cout << "name: " << this->name << endl << "age: " << this->age;
}