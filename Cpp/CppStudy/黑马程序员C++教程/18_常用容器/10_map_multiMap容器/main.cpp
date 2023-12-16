#include "iostream"
#include "map"

using namespace std;

void printf(map<int, int>& m) {
    for (auto it: m) {
        cout << "key = " << it.first << ", value = " << it.second << endl;
    }
}

//map容器 构造器和赋值
void test1() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    printf(m);
}

//大小和交换
void test2() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    cout << "m的大小: " << m.size() << endl;

    cout << "m是否为空: ";
    if (m.empty()) {
        cout << "为空" << endl;
    } else {
        cout << "不为空" << endl;
    }

    map<int, int> m2;
    m2.swap(m);

    cout << "m: " << endl;
    printf(m);
    cout << "\nm1: " << endl;
    printf(m2);
}

//查找和统计
void test3() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    cout << "key=1的数是否存在: " << m.find(1)->second << endl;
    cout << "统计 key=1 的个数: " << m.count(1) << endl;
}

class MyCompare {
public:
    bool operator() (const int v1, const int v2) const {
        return v1 > v2;
    }
};

//自定义排序
void test4() {
    map<int, int, MyCompare> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    for (auto it: m) {
        cout << "key = " << it.first << ", value = " << it.second << endl;
    }
}

int main() {
//    test1();
//    test2();
//    test3();
    test4();
    return 0;
}