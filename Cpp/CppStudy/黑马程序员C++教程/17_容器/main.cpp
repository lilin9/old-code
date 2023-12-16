#include <iostream>
#include "vector"
#include <algorithm>

using namespace std;

void printf(int val) {
    cout << val << endl;
}

void test1() {
    //1.创建一个vector容器
    vector<int> v;

    //2.往容器添加数据
    v.push_back(10);
    v.push_back(20);
    v.push_back(30);
    v.push_back(40);
    v.push_back(50);

    //3.通过迭代器访问容器中的数据
    //3-1.第一种遍历方式
//    auto begin = v.begin();
//    auto end = v.end();
//
//    while (begin != end) {
//        cout << *begin << endl;
//        begin++;
//    }

    //3-2.第二种遍历方式
//    for (auto begin = v.begin(); begin != v.end(); begin++) {
//        cout << *begin << endl;
//    }

    //3-3.第三种遍历方式
//    for_each(v.begin(), v.end(), printf);
}

//int main() {
//    test1();
//    return 0;
//}