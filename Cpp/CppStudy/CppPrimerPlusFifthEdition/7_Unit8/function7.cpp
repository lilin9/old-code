#include "iostream"

using namespace std;

/*
 * 修改程序清单8.14，使模板函数返回数组元素的总和，而不是显示数组的内容。程序应显示thing的总和
 * 以及所有dept的总和。
 */

struct Debts {
    char name[50];
    double amount;
};

template <typename T>
T showArray(T arr[], int n) {
    cout << "template A\n";
    T sum;
    for (int i = 0; i < n; ++i) {
        cout << arr[i] << ' ';
        sum += arr[i];
    }
    cout << endl;
    return sum;
}

template <typename T>
T showArray(T* arr[], int n) {
    cout << "template B\n";
    T sum;
    for (int i = 0; i < n; ++i) {
        cout << *arr[i] << ' ';
        sum += *arr[i];
    }
    cout << endl;
    return sum;
}

int main7() {
    int things[6] = {13, 31, 103, 301, 130};
    struct Debts mr_E[3] = {
        {"Ima Wolfe", 2400.0},
        {"Ura Foxe", 1300.0},
        {"Iby Stout", 1800.0}};
    double* pd[3];
    // set pointers to the amount members of the structures in the arr mr_E
    for (int i = 0; i < 3; ++i) {
        pd[i] = &mr_E[i].amount;
    }
    cout << "Listing Mr. E's counts of things: \n";
    // things is an array of int
    int thingsSum = showArray(things, 6);  // uses template A
    cout << "thingsSum: " << thingsSum << endl;
    cout << "Listing Mr . E's debts: \n";
    // pd is an array of pointers to double
    double debtSum = showArray(pd, 3);  // uses template B(more specialized)
    cout << "debtSum: " << debtSum << endl;
    return 0;
}