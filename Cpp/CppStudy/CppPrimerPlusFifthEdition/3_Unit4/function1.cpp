#include "iostream"
#include "string"

using namespace std;
/*
 * 编写一个c++程序，如下述输出范例所示的那样请求并显示信息：
 * What is your first name? Betty Sue
 * What is your last name? Yew
 * What letter grade do you deserve? B
 * what is your age? 22
 * Name: Yew. Betty Sue
 * Grade: C
 * Age: 22
 * 注意：该程序应该接受的名字包含多个单词。另外，程序将向下调整成绩，即向上调一个字母。假设
 * 用户请求 A、B或C，所以不必担心 D 和 F 之间的空挡。
 */
int main1() {
    string firstName;
    string lastName;
    char grade;
    int age;

    cout << "What is your first name? ";
    getline(cin, firstName);
    cout << "What is your last name? ";
    getline(cin, lastName);
    cout << "What letter grade do you deserve? ";
    cin >> grade;
    cout << "What is your age? ";
    cin >> age;


    cout << "\nName: " << lastName << ". " << firstName;
    cout << "\nGrade: " << char (grade + 1);
    cout << "\nAge: " << age;
    return 0;
}