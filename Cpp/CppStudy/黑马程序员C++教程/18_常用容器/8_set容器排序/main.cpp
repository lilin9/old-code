#include "iostream"
#include "set"
#include "cstring"

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

class MyCompare {
public:
    bool operator() (int v1, int v2) const {
        return v1 > v2;
    }
};

class PersonCompare {
public:
    bool operator() (const Person& p1, const Person& p2) const {
        return p1.age < p2.age;
    }
};

template<class T>
void printf(T& s) {
    for (auto it: s) {
        cout << it << " ";
    }
    cout << endl;
}

//指定排序规则
void test1() {
    //指定set排序为从大到小
    set<int, MyCompare> s1;

    s1.insert(10);
    s1.insert(40);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);

    printf(s1);
}

//set容器存放自定义数据类型
void test2() {
    set<Person, PersonCompare> s1;
    s1.insert(Person("tony", 12));
    s1.insert(Person("tom", 16));
    s1.insert(Person("alex", 14));
    s1.insert(Person("mary", 15));

    for (const auto& it: s1) {
        cout << it.name << ", " << it.age << endl;
    }
}

int main() {
//    test1();
    test2();
    return 0;
}