#include <iostream>
#include <cstring>

using namespace std;

class Animal {
    public:
    Animal() {
        cout << "Animal构造函数" << endl;
    }
    
    public:
    virtual void speak() = 0;
    
    //虚析构函数
    // virtual ~Animal() {
    //     cout << "Animal析构函数" << endl;
    // }

    //纯虚析构函数，需要在类外实现
    virtual ~Animal() = 0;
};

//animal 纯虚析构函数的实现
Animal::~Animal() {
    cout << "Animal析构函数" << endl;
}

class Cat : public Animal {
    public:
    string* name;
    
    public:
    Cat(string name) {
        cout << "Cat构造函数" << endl;
        this -> name = new string(name);
    }
    
    public:
    void speak() {
        cout << *name << "小猫喵喵喵!" << endl;
    }

    ~Cat() {
        if (this -> name != NULL) {
        cout << "Cat析构函数" << endl;
            delete this -> name;
            this -> name = NULL;
        }
    }
};

// int main() {
//     Animal* animal = new Cat("tom");
//     animal->speak();
//     delete animal;
//     return 0;
// }
