#include "iostream"
#include "cstring"

using namespace std;

//�ַ���������
void test1() {
    const char *str = "hello world";
    string s1(str);
    cout << "s1 = " << s1 << endl;

    string s2(s1);
    cout << "s2 = " << s2 << endl;

    string s3(10, 'h');
    cout << "s3 = " << s3 << endl;
}

//�ַ�����ƴ��
void test2() {
    string s1 = "�Ұ�";
    s1 = s1 + "�Ծ���";
    cout << s1 << endl;

    s1.append("��Ҳ����ż���");
    cout << s1 << endl;
}

//�ַ����Ĳ���
void test3() {
    string str = "i love you ten thousand years old";
    cout << str.find('o') << endl;
    cout << str.rfind('o') << endl;
}

//�ַ������滻
void test4() {
    string str = "i love you ten thousand years old";
    str.replace(2, 4, "hide");
    cout << str << endl;
}

//�ַ����Ƚ�
void test5() {
    string str1 = "love";
    string str2 = "like";
    if (str1.compare(str2) == 1) {
        cout << str1 << " != " << str2;
    } else {
        cout << str1 << " = " << str2;
    }
}

//��ȡ�ַ���
void test6() {
    string str = "love";

    //��ʽһ
    for (int i = 0; i < str.size(); ++i) {
        cout << str[i] << "\t";
    }
    cout << endl;

    //��ʽ��
    for (int i = 0; i < str.size(); ++i) {
        cout << str.at(i) << "\t";
    }
    cout << endl;
}

//�ַ����Ĳ�����ɾ��
void test7() {
    string str = "love";
    //����
    str.insert(str.length(), "like");
    cout << str << endl;

    //ɾ��
    str.erase(3, str.length()-4);
    cout << str << endl;
}

//���ַ������ִ�
void test8() {
    string str = "tom@gmail.com";
    int index = str.find('@');
    cout << str.substr(0, index) << endl;
}

int main() {
    test8();
    return 0;
}