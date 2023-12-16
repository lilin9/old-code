#include <iostream>

/*
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom
 * 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符
 * 串中的每个字符只能在赎金信字符串中使用一次。)
 *
 * 注意：
 * 你可以假设两个字符串均只含有小写字母。
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */

using namespace std;

bool canConstruct(string ransomNote, string magazine) {
    //判空
    if (ransomNote.empty() || magazine.empty()) {
        return false;
    }

    int words[26] = {0};
    for (unsigned char item: magazine) {
        words[int(item) - 97]++;
    }

    for (unsigned char item: ransomNote) {
        words[int(item) - 97]--;
    }

    for (int item: words) {
        if (item < 0) {
            return false;
        }
    }
    return true;
}

int main5() {
    string ransomNote = "a";
    string magazine = "b";    //false
//    string ransomNote = "aa";
//    string magazine = "ab";    //false
//    string ransomNote = "aa";
//    string magazine = "aab";    //true

    if (canConstruct(ransomNote, magazine)) {
        cout << "true" << endl;
    } else {
        cout << "false" << endl;
    }
    return 0;
}