#include <iostream>

/*
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
 * 该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 *
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *
 * 限制：
 * 1 <= k < s.length <= 10000
 */

using namespace std;

void reverse(string &s, int start, int end) {
    if (s.empty()) return;

    char temp = ' ';
    for (; start < end; ++start, --end) {
        temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }
}

string reverseLeftWords(string s, int n) {
    if (s.empty()) return nullptr;

    //将字符串整体翻转
    int length = s.size();
    reverse(s, 0, length - 1);

    //翻转要翻转的字符串若干个字符
    reverse(s, length - n, length - 1);
    //翻转剩下的字符
    reverse(s, 0, length - n - 1);

    return s;
}

int main5() {
//    string s = "abcdefg";
//    int k = 2;  //cdefgab


    string s = "lrloseumgh";
    int k = 6;  //umghlrlose


    cout << reverseLeftWords(s, k) << endl;
    return 0;
}