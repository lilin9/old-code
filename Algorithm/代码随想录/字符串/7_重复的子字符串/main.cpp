#include <iostream>
#include <algorithm>

/*
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，
 * 并且长度不超过10000。
 *
 * 示例 1:
 * - 输入: "abab"
 * - 输出: True
 * - 解释: 可由子字符串 "ab" 重复两次构成。
 *
 * 示例 2:
 * - 输入: "aba"
 * - 输出: False
 *
 * 示例 3:
 * - 输入: "abcabcabcabc"
 * - 输出: True
 * - 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */

bool repeatedSubstringPattern(std::string s) {
    //判空，单个字符
    if (s.empty() || s.size() == 1) {
        return false;
    }

    std::string newStr = s + s;
    //去除拼接后的 newStr 的第一个字符和最后一个字符
    newStr.erase(newStr.begin());
    newStr.erase(newStr.end() - 1);

    //判断此时的 newStr 内是否还能匹配出一个 s
    if (newStr.find(s) == std::string::npos) return false;
    return true;
}

int main7() {
//    std::string s = "abab"; //true
//    std::string s = "aba"; //false
//    std::string s = "abcabcabcabc"; //true
    std::string s = "ababab"; //true

    if (repeatedSubstringPattern(s)) {
        std::cout << "true" << std::endl;
    } else {
        std::cout << "false" << std::endl;
    }
    return 0;
}