#include "iostream"
#include "functional"
#include "vector"
#include "algorithm"

using namespace std;

//算符仿函数
void test1() {
    //一元仿函数 取反
    negate<int> n;
    cout << n(10) << endl;

    //二元仿函数 两数相加
    plus<int> p;
    cout << p(10, 20) << endl;
}

//关系仿函数
void test2() {
    vector<int> v;
    v.push_back(10);
    v.push_back(40);
    v.push_back(30);
    v.push_back(50);
    v.push_back(20);

    for (auto it: v) {
        cout << it << " ";
    }
    cout << endl;

    //降序排序
    sort(v.begin(), v.end(), greater<int>());

    for (auto it: v) {
        cout << it << " ";
    }
    cout << endl;
}

//逻辑仿函数
void test3() {
    vector<bool> v1;
    v1.push_back(true);
    v1.push_back(false);
    v1.push_back(true);
    v1.push_back(false);

    for (auto it: v1) {
        cout << it << " ";
    }
    cout << endl;

    vector<bool> v2;
    v2.resize(v1.size());
    transform(v1.begin(), v1.end(), v2.begin(), logical_not<bool>());

    for (auto it: v2) {
        cout << it << " ";
    }
    cout << endl;
}

int main() {
//    test1();
//    test2();
    test3();
    return 0;
}