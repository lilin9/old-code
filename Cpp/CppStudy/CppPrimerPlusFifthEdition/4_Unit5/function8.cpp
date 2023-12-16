#include "iostream"
#include "string"

using namespace std;
/*
 * 编写一个满足前一个练习中描述的程序，但使用 string 对象而不是字符数组。请在程序中包括头文件 string，
 * 并使用关系操作符来进行比较测试。
 */
int main8() {
    string words;
    int count = 0;
    cout << "Enter words(to stop, type the word done)" << endl;
    cin >> words;
    while (words != "done") {
        cin >> words;
        count ++;
    }
    cout << "You entered a total of " << count <<" words." << endl;
    return 0;
}