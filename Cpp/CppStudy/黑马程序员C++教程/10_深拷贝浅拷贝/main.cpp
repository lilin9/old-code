#include <iostream>

using namespace std;

class Person {
   public:
    string name;
    int age;
    int* height;  // 身高，用指针接收

    // 无参构造
    Person() { cout << "无参构造函数" << endl;
     }
    // 有参构造
    Person(string name, int age, int height) {
        cout << "有参构造函数" << endl;
        this->name = name;
        this->age = age;
        this->height = new int(height);
    }
    ~Person() {
        cout << "析构函数" << endl;
        if (this->height != NULL) {
            delete this->height;
            this->height = NULL;
        }
    }
    // 拷贝构造
    Person(const Person& p) {
        cout << "拷贝构造函数" << endl;
        this->name = p.name;
        this -> height = new int(*p.height);
    }
};

int main10() {
    Person p1 = Person("tony", 12, 170);
    cout << "姓名: " << p1.name << ", 年龄: " << p1.age << ", 身高: " << *p1.height << endl;

    Person p2 = Person(p1);
    cout << "姓名: " << p2.name << ", 年龄: " << p2.age << ", 身高: " << *p2.height << endl;
    return 0;
}
