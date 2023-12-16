#include "iostream"

using namespace std;
/*
 * 编写一个程序，它要求用户首先输入其名，而不是其姓；然后程序使用一个逗号和空格将姓和名组合起来，
 * 并储存和显示组合结果。请使用 char 数组和头文件 cstring 中的函数。下面是该程序运行时的情形：
 * Enter your first name: Flip
 * Enter your last name: Fleming
 * Here's the information in a single string: Fleming, Flip
 */
int main4() {
    string firstName;
    string lastName;

    cout << "Enter your first name: " ;
    cin >> firstName;
    cout << "Enter your last name: ";
    cin >> lastName;

    cout << "Here's the information in a single string: " << lastName << ", " << firstName;
    return 0;
}