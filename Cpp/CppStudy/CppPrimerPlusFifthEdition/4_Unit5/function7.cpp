#include "iostream"
#include "string.h"

using namespace std;
/*
 * ��дһ��������ʹ��һ�� char �����ѭ����ÿ�ζ�ȡһ�����ʣ�֪���û����� done Ϊֹ�����
 * �ó���ָ���û������˶��ٸ����ʣ������� done ���ڣ��������Ǹó�������������
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