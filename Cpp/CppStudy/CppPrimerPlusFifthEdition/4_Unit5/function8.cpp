#include "iostream"
#include "string"

using namespace std;
/*
 * ��дһ������ǰһ����ϰ�������ĳ��򣬵�ʹ�� string ����������ַ����顣���ڳ����а���ͷ�ļ� string��
 * ��ʹ�ù�ϵ�����������бȽϲ��ԡ�
 */
int main8() {
    string words;
    int count = 0;
    cout << "Enter words(to stop, type the word done)" << endl;
    cin >> words;
    while (words != "done") {
        cin >> words;
        count ++;
    }
    cout << "You entered a total of " << count <<" words." << endl;
    return 0;
}