#include "iostream"
#include "string"

using namespace std;
/*
 * 编写一个使用嵌套循环的程序，要求用户输入一个值，指出要显示多少行。然后，程序将显示相应行数的
 * 星号，在其中第一行包括一个星号，第二行包括两个星号，以此类推。每一行包括的字符数等于用户指定
 * 的行数，在星号不够的情况下，在星号前面加上句点。在程序的运行情况如下：
 * Enter number of rows: 5
 * ....*
 * ...**
 * ..***
 * .****
 * *****
 */
int main9() {
    int num = 0;
    cout << "Enter number of rows: ";
    cin >> num;

    for (int i = 0; i < num; ++i) {
        for (int k = i; k < num-1; ++k) {
            cout << ".";
        }
        for (int j = num - i; j < num+1; ++j) {
            cout << "*";
        }
        cout << endl;
    }
    return 0;
}