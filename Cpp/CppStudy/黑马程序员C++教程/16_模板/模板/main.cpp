#include <iostream>

using namespace std;

// 函数模板
template <typename T>
void mySwap(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

// int main() {    //学到模板案例
//     int a = 10;
//     int b = 20;

//     // 自动类型推导
//     //    mySwap(a, b);
//     // 主动指定类型
//     mySwap<int>(a, b);

//     cout << "a=" << a << endl
//          << "b=" << b;
//     return 0;
// }
