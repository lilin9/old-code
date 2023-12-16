#include "iostream"
#include "cctype"

using namespace std;

/*
 * 编写一个程序，它每次读取一个单词，直到用户只输入q。然后，该程序指出有多少个单词以元音
 * 字母打头，有多少个单词以辅音打头，还有多少个单词不属于这两类。为此，方法之一是，使用isalpha()
 * 来区分以字母和其他字符打头的单词，然后对于通过了isalpha()测试的单词，使用if或switch语句来确定
 * 哪些以元音打头。该程序的运行情况如下：
 * Enter words(q to quit):
 * The 12 awesome oxen ambled
 * quietly across 15 meters of lawn. q
 * 5 words beginning with vowels
 * 4 words beginning with consonants
 * 2 others
 */
int main7() {
    string input;
    //元音
    int vowel = 0;
    //辅音
    int consonant = 0;
    //其他
    int other = 0;
    //元音字符串
    string vowelStr = "aeiouAEIOU";

    cout << "Enter words(q to quit): " << endl;
    while (cin >> input) {
        if (input == "q") {
            break;
        }

        if (isalpha(input[0])) {
            if (vowelStr.find(input[0]) != string::npos) {
                vowel++;
            } else {
                consonant++;
            }
        } else {
            other++;
        }
    }

    cout << vowel << " words beginning with vowels" << endl;
    cout << consonant << " words beginning with consonants" << endl;
    cout << other << " others" << endl;
    return 0;
}