#include <iostream>
#include <stack>
#include <algorithm>

/*
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 示例：
 * - 输入："abbaca"
 * - 输出："ca"
 * - 解释：例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
 * 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa"
 * 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *
 * 提示：
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 */

using namespace std;

string removeDuplicates(string s) {
    //判空
    if (s.empty()) return "";

    string result;
    //遍历字符串
    for (char item: s) {
        //判断当前字符是否等于 priorityQueue 最后一个字符
        if (result.empty() || result.back() != item) {
            //priorityQueue 加上当前字符
            result.push_back(item);
        } else {
            //否则弹出 priorityQueue 最后一个字符
            result.pop_back();
        }
    }

    return result;
}

string removeDuplicates_old(string s) {
    //判空
    if (s.empty()) return "";

    //定义一个栈
    stack<char> sta;

    //遍历字符串
    for (char i : s) {
        //判断字符串当前元素与栈顶元素是否相等
        if (sta.empty() || sta.top() != i) {
            //将当前元素入栈
            sta.push(i);
        } else {
            //否则弹出栈顶元素
            sta.pop();
        }
    }

    //将栈里面的元素重新组成字符串
    string result;
    while (!sta.empty()) {
        result += sta.top();
        sta.pop();
    }

    //将字符串翻转
    reverse(result.begin(), result.end());
    //返回结果
    return result;
}

int main4() {
    string s = "abbaca";    //ca

    cout << removeDuplicates(s) << endl;
    return 0;
}