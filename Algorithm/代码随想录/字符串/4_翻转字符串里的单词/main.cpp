#include <iostream>

/*
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */

using namespace std;

//翻转字符串
void reverse(string &s, int start, int end) {
    if (s.empty()) return;

    char temp = ' ';
    for (; start < end; ++start, --end) {
        temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }
}

//移除字符串空格
void removeBlank(string &s) {
    //定义一个慢指针
    int slow = 0;

    //循环遍历
    int size = s.size();
    for (int quick = 0; quick < size; ++quick) {
        //快指针没有指向空格的话
        if (s[quick] != ' ') {
            //慢指针没有指向第一个元素的情况下
            if (slow != 0) {
                //将慢指针指向的值赋成空格
                s[slow++] = ' ';
            }
            //循环，直到遇到空格结束
            while (quick < size && s[quick] != ' ') {
                s[slow++] = s[quick++];
            }
        }
    }
    //重新设置字符串的长度
    s.resize(slow);
}

string reverseWords(string s) {
    //首先去掉 s 的多余空格
    removeBlank(s);

    int size = s.size();

    //再首尾翻转 s
    reverse(s, 0, size-1);

    //翻转字符串里面的镜像单词
    int start = 0;
    for (int end = 0; end < size; ++end) {
        //遇到空格的时候
        if (s[end + 1] == ' ' || end == size - 1) {
            //翻转镜像单词
            reverse(s, start, end);
            //让 start 指针指向下一个单词的开始
            start = end + 2;
        }
    }
    return s;
}

int main4() {
    string s = "a good   example";   //example good a
//    string s = "  hello world!  ";   //world! hello
//    string s = " the sky is blue";   //blue is sky the

    cout << reverseWords(s) << endl;
    return 0;
}