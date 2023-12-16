#include <iostream>

using namespace std;

/**
 * 修改程序清单 9.8，用string对象代替字符数组。这样，该程序将不再需要检查输入的字符串是否
 * 过长，同时可以将输入字符串同字符串""进行比较，以判断是否为空行。
 */

const int ArSize = 10;

// function prototype
void strCount(const string& str);

int main2() {
    string input;
    // char next;

    cout << "Enter a line: \n";
    getline(cin, input);
     while (input != "") {
        strCount(input);
        cout << "Enter next line(empty line to quit): \n";
        getline(cin, input);
    }

    cout << "Bye!\n";
    return 0;
}

void strCount(const string& str) {
    static int total = 0;  // static local variable
    int count = 0;         // automatic local variable

    cout << "\"" << str << "\" contains ";
    for (auto item : str) {  // go to end of string
        count++;
    }
    total += count;
    cout << count << " characters\n";
    cout << total << " characters total\n";
}