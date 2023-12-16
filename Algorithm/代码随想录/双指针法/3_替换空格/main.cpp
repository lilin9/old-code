#include <iostream>

/*
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */

using namespace std;

string replaceSpace(string s) {
    //判空
    if (s.empty()) return "";

    //获取字符串长度
    int oldLength = s.size();
    //获取字符串中空格的个数
    int count = 0;
    for (char item: s) {
        if (item == ' ') {
            count++;
        }
    }

    //扩充字符串到 把字符串 s 中的每个空格替换成"%20" 的长度
    s.resize(oldLength + 2 * count);

    //定义两个指针，分别指向字符串没有扩充前的尾部和扩充后的尾部
    int oldPoint = oldLength - 1;
    int newPoint = s.size() - 1;
    //在循环中，从后往前替换字符串的字符
    string temp = "02%";
    while (oldPoint < newPoint) {
        //当 oldPoint 指向空格时
        if (s[oldPoint] == ' ') {
            //newPoint 开始填充 %20
            for (char item: temp) {
                s[newPoint] = item;
                newPoint--;
            }
            oldPoint--;
            continue;
        }

        s[newPoint] = s[oldPoint];

        //两指针前移
        oldPoint--;
        newPoint--;
    }

    return s;
}

int main3() {
    string s = "     "; //"%20%20%20%20%20"
//    string s = "We are h"; //We%20are%20h

//    string s = "We are happy."; //We%20are%20happy.

    cout << replaceSpace(s) << endl;
    return 0;
}