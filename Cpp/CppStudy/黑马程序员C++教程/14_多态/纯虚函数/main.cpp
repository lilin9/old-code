#include <iostream>

using namespace std;

//纯虚函数-即接口
//抽象类
class Base {
    public:
    virtual void func() =0;
};

class Child: public Base {
    public:
    void func() {
        cout << "Child类" << endl;
    }
};

// int main() {
//    Base* base = new Child();
//    base -> func();
//    delete base;
//    return 0;
// }
