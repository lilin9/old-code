#include "iostream"
#include "cctype"
#include "strings.h"

using namespace std;

/*
 * 编写一个程序，读取键盘输入，知道遇到 @ 符号为止，并回显输入（数字除外），同时将大写字符
 * 转换为小写，将小写字符转换成大写（别忘了 cctype 函数系列）。
 */
int main1() {
    char input[20];

    cout << "Enter words(@ stop): ";
    cin >> input;

    while (strcmp(input, "@") != 0) {
        for (char item: input) {
            if (isupper(item) != 0) {   //大写字母
                cout << (char) tolower(item);
            } else if (islower(item) != 0) {   //小写字母
                cout << (char) toupper(item);
            } else {
                cout << " ";
            }
        }
        cout << endl;

        cout << "Enter words(@ stop): ";
        cin >> input;
    }

    return 0;
}