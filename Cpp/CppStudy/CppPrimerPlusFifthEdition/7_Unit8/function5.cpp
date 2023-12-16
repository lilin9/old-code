#include "iostream"

using namespace std;

/*
 * 编写模板函数max5()，它将一个包含5个T类型元素的数组作为参数，并返回数组中最大的元素（由于长度固定，
 * 因此可以在循环中使用硬编码，而不必通过参数来传递）。在一个程序中使用该函数，将T替换为一个包含5个int
 * 值的数组和一个包含5个double值的数组，以测试该函数。
 */

template<class T>
T max(T arr[5]) {
    T temp;
    for (int i = 0; i < 5; ++i) {
        if (arr[i] >= temp) {
            temp = arr[i];
        }
    }
    return temp;
}

int main5() {
    int intArr[5] = {3, 4, 5, 2, 8};
    double doubleArr[5] = {0.5, 0.2, 0.6, 0.9, 0.1};

    cout << "max number of intArr: " << max(intArr) << endl;
    cout << "max number of doubleArr: " << max(doubleArr) << endl;
    return 0;
}