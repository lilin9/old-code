#include <cstring>
#include <iostream>

using namespace std;

class Person {
   public:
    string name;
    int age;

    Person(string name, int age) {
        this->name = name;
        this->age = age;
    }
};

template <typename T>
string isEquals(T& a, T& b) {
    if (a == b) {
        return "相等";
    } else {
        return "不相等";
    }
}

// 当有自定义数据类型时，调用此方法
template <>
string isEquals(Person& a, Person& b) {
    if (a.age == b.age) {
        return "相等";
    } else {
        return "不相等";
    }
}

void test1() {
    int a = 10;
    int b = 10;

    cout << isEquals(a, b) << endl;
}

void test2() {
    Person p1("tom", 12);
    Person p2("tony", 12);

    cout << isEquals(p1, p2) << endl;
}

// int main() {
//     test1();
//     test2();
//     return 0;
// }
