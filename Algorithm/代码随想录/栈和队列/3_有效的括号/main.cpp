#include <iostream>
#include <stack>

/*
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * - 左括号必须用相同类型的右括号闭合。
 * - 左括号必须以正确的顺序闭合。
 * - 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * - 输入: "()"
 * - 输出: true
 *
 * 示例 2:
 * - 输入: "()[]{}"
 * - 输出: true
 *
 * 示例 3:
 * - 输入: "(]"
 * - 输出: false
 *
 * 示例 4:
 * - 输入: "([)]"
 * - 输出: false
 *
 * 示例 5:
 * - 输入: "{[]}"
 * - 输出: true
 */

using namespace std;

bool isValid(string s) {
    int size = s.size();
    //参数判空，如果 s 个数为奇数，也直接返回
    if (s.empty() || size % 2 != 0) {
        return false;
    }

    //定义一个栈
    stack<char> stack;
    //遍历字符串
    for (int i = 0; i < size; ++i) {
        //入栈
        if (s[i] == '(') stack.push(')');
        else if (s[i] == '[') stack.push(']');
        else if (s[i] == '{') stack.push('}');
        //两种情况：
        //第一种，字符串还没有遍历完的情况下，栈先空了
        //第二种，当前字符串元素和栈顶元素不相等
        else if ((i < size && stack.empty()) || s[i] != stack.top()) return false;
        //其他情况，就弹出栈顶元素
        else stack.pop();
    }

    //遍历完字符串后，如果栈不为空，直接返回 false
    if (!stack.empty()) return false;
    //不是就返回 true
    return true;
}

int main3() {
//    string s = "()";    //true
//    string s = "()[]{}";    //true
//    string s = "(]";    //false
//    string s = "([)]";    //false
    string s = "{[]}";    //true

    if (isValid(s)) {
        cout << "true" << endl;
    } else {
        cout << "false" << endl;
    }
    return 0;
}