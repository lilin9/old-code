#include <iostream>

using namespace std; //学到继承

class Person {
    public:
    int a;
    int b;

    Person(int a, int b) {
        this -> a = a;
        this->b = b;
    }

    //1-1. 成员函数实现加法运算符重载
    Person operator+ (Person& p) {
        return Person(this->a + p.a, this->b + p.b);
    }
};

//1-2. 全局函数实现运算符重载
// Person operator+ (Person& p1, Person& p2) {
//     return Person(p1.a + p2.a, p1.b + p2.b);
// }

//2. 全局函数实现左移运算符重载
ostream& operator<< (ostream& cout, Person& p) {
    cout << "person={a:" << p.a << ", b:" << p.b << "}";
    return cout;
}

void add() {
    Person p1 = Person(10, 10);
    Person p2 = Person(10, 10);
    Person p3 = p1 + p2;
    cout << "a=" << p3.a << ", b=" << p3.b << endl;
}

void leftMove() {
    Person p1 = Person(10, 10);
    cout << p1 << endl;
}

int main12() {
//    add();
   leftMove();
   return 0;
}
