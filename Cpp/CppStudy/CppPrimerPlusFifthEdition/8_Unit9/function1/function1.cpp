#include <iostream>
#include "golf.cpp"
#include "cstring"

using namespace std;

/**
 * 下面是一个头文件：
 * //golf.h -- for pe9-1.cpp
 *
 * const int Len = 40;
 * struct golf {
 *      char fullname[Len];
 *      int handicap;
 * }
 *
 * //non-interactive version:
 * // function sets golf structure to provided name, handicap
 * // using values passed as arguments to the function
 * void setgolf(golf &g, const char * name, int hc);
 *
 * //interactive version:
 * //function solicits name and handicap from user
 * //and sets the members of f to the values entered
 * //returns 1 if name is entered, 0 if name is empty string
 * int setgolf(golf &g);
 *
 * //function resets handicap to new value
 * void handicap(golf &g, int hc);
 *
 * //function displays contents of golf structure
 * void showgolf(const golf &g);
 *
 * setgolf()被重载，可以使用其第一个版本：
 * golf ann;
 * setgolf(ann, "Ann Birdfree", 24);
 * 上述函数调用提供了储存在 ann 结构中的信息。可以这样使用其第二个版本：
 * golf andy;
 * setgolf(andy);
 * 上述函数将提示用户输入姓名和等级，并将它们储存在 andy 结构中。这个函数可以（但是不一定必须）
 * 在内部使用第一个版本。
 * 根据这个头文件，创建一个多文件程序。其中的一个文件名为golf.cpp，它提供了与头文件中的原型
 * 匹配的函数定义；另一个文件应包含main()，并演示原型化函数的所有特性。例如，包括一个让用户
 * 输入的循环，并使用输入的数据来填充一个由golf结构组成的数组，数组被填满或用户将高尔夫选手的
 * 姓名设置为空字符串时，循环将结束。main()函数只使用头文件中原型化的函数来访问golf结构。
 */
int main1() {
    Golf ann;
    setgolf(ann, "Ann Birdfree", 24);
    showgolf(ann);

    cout << endl;

    Golf andy;
    setgolf(andy);
    showgolf(andy);

    cout << endl;

    Golf golfArr[5];
    for (int i = 0; i < 5; i++) {
        Golf golf;
        int result = setgolf(golf);
        if (result == 0) {
            break;
        }
        golfArr[i] = golf;
    }

    cout << endl;
    for (int i = 0; i < 5; i++) {
        showgolf(golfArr[i]);
    }
    return 0;
}

void setgolf(Golf& g, const char* name, int hc) {
    strcpy(g.fullname, name);
    g.handicap = hc;
}

int setgolf(Golf& g) {
    string name;
    int handicap = 0;

    cout << "Enter the golfer's name: ";
    cin >> name;
    cout << "Enter the golfer's handicap: ";
    cin >> handicap;


    strcpy(g.fullname, name.c_str());
    g.handicap = handicap;

    if (name.empty()) {
        return 0;
    } else {
        return 1;
    }
}

void handicap(Golf& g, int hc) {
    g.handicap = hc;
}

void showgolf(const Golf& g) {
    cout << "Golfer's name is " << g.fullname << endl;
    cout << "Golfer's handicap is " << g.handicap << endl;
}
