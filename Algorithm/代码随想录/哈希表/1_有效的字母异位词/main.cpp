#include <iostream>

/*
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 示例1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 说明: 你可以假设字符串只包含小写字母。
 */

bool isAnagram(std::string s, std::string t) {
    if (s.empty() || t.empty()) {
        return false;
    }

    int words[26] = {0};

    //在数组中对 s 中出现的字符做个记录
    for (char item: s) {
        words[int(item) - 97]++;
    }

    //根据 t 中的字符对数组记录进行增删
    for (char item: t) {
        words[int(item) - 97]--;
    }

    for (int i = 0; i < 26; ++i) {
        if (words[i] != 0) {
            return false;
        }
    }
    return true;
}

int main1() {
    //false
    std::string s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    std::string t = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbba" ;
//    std::string s = "rat";
//    std::string t = "car";  //false
//    std::string s = "anagram";
//    std::string t = "nagaram";  //true

    if (isAnagram(s, t) == 0) {
        std::cout << "false" << std::endl;
    } else {
        std::cout << "true" << std::endl;
    }
    return 0;
}