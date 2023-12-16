#include "iostream"

using namespace std;

/*
 * 编写一个程序，它使用下列函数：
 * Fill_array() 将一个 double 数组的名称和长度作为参数。它提示用户输入 double 值，并将这些值储存到
 * 数组中。当数组被填满或用户输入了非数字时，输入将停止，并返回实际输入了多少个数字。
 * Show_array() 将一个 double 数组的名称和长度作为参数，并显示该数组的内容。
 * Reverse_array() 将一个 double 数组的名称和长度作为参数，并将储存在数组中的值的顺序反转。
 * 程序将使用这些函数来填充数组，然后显示数组；反转数组，然后显示数组；反转数组中除第一个和
 * 最后一个元素之外的所有元素，然后显示数组。
 */

int FillArray(double *arr, int length) {
    double number = 0;
    int count = 0;

    cout << "Enter one number: ";
    while (cin >> number && count < length-1) {
        arr[count] = number;
        count ++;
        cout << "Enter one number: ";
    }

    return count;
}

void ShowArray(double *arr, int length) {
    cout << "values of array is: ";
    for (int i = 0; i < length; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;
}

void ReverseArray(double *arr, int length) {
    int i, j;
    for (i = 1, j = length-2; i != j; ++i, --j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

int main6() {
    int length = 10;
    double arr[length];
    length = FillArray(arr, length);
    ShowArray(arr, length);
    cout << "Reverse Array......" << endl;
    ReverseArray(arr, length);
    ShowArray(arr, length);

    return 0;
}