#include <iostream>

/*
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起, 每计数至 2k 个字符，就反转这 2k 个字符中的前 k
 * 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *
 * 示例:
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 */

//这个方法专门用来反转字符串
//反转 left 到 right 中间的字符
void reverse(std::string &str, int left, int right) {
    //判空
    if (str.empty()) {
        return;
    }

    //进行字符串反转
    char temp;
    while (left < right) {
        temp = str[left];
        str[left] = str[right];
        str[right] = temp;

        left++;
        right--;
    }
}

std::string reverseStr(std::string s, int k) {
    if (s.empty()) {
        return nullptr;
    }

    //字符串的长度
    int length = s.size();
    for (int i = 0; i < length; i += (2 * k)) {
        //如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
        if (i + k < length) {
            reverse(s, i, i + k - 1);
        } else {
            //如果剩余字符少于 k 个，则将剩余字符全部反转。
            reverse(s, i, length - 1);
        }
    }

    return s;
}

int main2() {
    std::string s = "abcd";
    int k = 2;  //bacd

//    std::string s = "abcdefg";
//    int k = 2;  //bacdfeg

    std::cout << reverseStr(s, k) << std::endl;
    return 0;
}