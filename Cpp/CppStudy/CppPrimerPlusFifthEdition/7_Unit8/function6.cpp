#include "iostream"
#include "cstring"

using namespace std;

/*
 * 编写模板函数maxn()，它将由一个T类型元素组成的数组和一个表示数组元素数目的整数作为参数，
 * 并返回数组中最大的元素。在程序对它进行测试，该程序使用一个包含6个int元素的数组和一个包含
 * 4个double元素的数组来调节该函数。程序还包含一个具体化，它将char指针数组和数组中的指针数量
 * 作为参数，并返回最长的字符串的地址。如果有多个这样的字符串，则返回其中第一个字符串的地址。
 * 使用由5个字符串指针组成的数组来测试该具体化。
 */

template<class T>
T maxn(T arr[], int count) {
    T temp = arr[0];
    for (int i = 0; i < count; ++i) {
        if (arr[i] >= temp) {
            temp = arr[i];
        }
    }
    return temp;
}

char* maxn(char *arr[], int count) {
    char *strAddr;
    for (int i = 0; i < count; ++i) {
        if (strlen(strAddr) < strlen(arr[i])) {
            strAddr = arr[i];
        }
    }
    return strAddr;
}

int main7() {
    int intArr[5] = {3, 4, 5, 2, 8};
    double doubleArr[5] = {0.5, 0.2, 0.6, 0.9, 0.1};

    char str1[] = "Hello";
    char str2[] = "World";
    char str3[] = "I love you";
    char str4[] = "Good morning";
    char str5[] = "Goodbye";
    char *strArr[] = {str1, str2, str3, str4, str5};


    cout << "max number of intArr: " << maxn(intArr, 5) << endl;
    cout << "max number of doubleArr: " << maxn(doubleArr, 5) << endl;
    cout << "longest string address: " << maxn(strArr, 5) << endl;
    return 0;
}