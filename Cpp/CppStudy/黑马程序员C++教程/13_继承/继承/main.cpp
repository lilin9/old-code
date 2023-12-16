#include <iostream>

using namespace std;

class Parent {
    public:
    int a;
    private:
    int b;
    protected:
    int c;
};

class Child: public Parent {
    public:
    int d;
};

void test1() {
    Child child;
    cout << sizeof(child) << endl;
}

int main() {
   test1();
   return 0;
}
