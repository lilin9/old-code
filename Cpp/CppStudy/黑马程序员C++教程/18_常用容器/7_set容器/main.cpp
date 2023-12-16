#include "iostream"
#include "set"

using namespace std;

template<class T>
void printf(T& s) {
    for (auto it: s) {
        cout << it << " ";
    }
    cout << endl;
}

//构造器
void test1() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);

    printf<set<int>>(s1);
}

//大小和交换
void test2() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);
    printf<set<int>>(s1);

    cout << "s1的大小 "<< s1.size() << endl;

    if (s1.empty()) {
        cout << "s1为空" << endl;
    } else {
        cout << "s1不为空" << endl;
    }

    set<int> s2;
    s2.swap(s1);
    cout << "s1: ";
    printf<set<int>>(s1);
    cout << "\ns2: ";
    printf<set<int>>(s2);
}

//查询和统计
void test3() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);
    printf<set<int>>(s1);

    auto it = s1.find(70);
    if (it == s1.end()) {
        cout << "s1里面没有要查找的数据" << endl;
    } else {
        cout << "要查找数据的位置是 " << &it << endl;
    }

    cout << "s1中70的个数是" << s1.count(70) << endl;
}

//multiset 和 set 的区别
//multiset 可以插入重复的值; set 不可以插入重复的值
void test4() {
    multiset<int> ms;
    ms.insert(10);
    ms.insert(10);
    ms.insert(40);
    ms.insert(40);
    ms.insert(20);
    ms.insert(30);
    ms.insert(50);

    printf<multiset<int>>(ms);
}

int main() {
//    test1();
//    test2();
//    test3();
    test4();
    return 0;
}