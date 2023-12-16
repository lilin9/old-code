#include "iostream"
#include "string.h"

using namespace std;
/*
 * 编写一个程序，它使用一个 char 数组和循环来每次读取一个单词，知道用户输入 done 为止。随后，
 * 该程序指出用户输入了多少个单词（不包括 done 在内）。下面是该程序的运行情况：
 * Enter words(to stop, type the word done):
 * anteater birthday category dumpster
 * envy finagle geometry done for sure
 * You entered a total of 7 words.
 */
int main7() {
    char words[20];
    int count = 0;
    cout << "Enter words(to stop, type the word done)" << endl;
    cin >> words;
    while (strcmp(words, "done") != 0) {
        cin >> words;
        count ++;
    }
    cout << "You entered a total of " << count <<" words." << endl;
    return 0;
}