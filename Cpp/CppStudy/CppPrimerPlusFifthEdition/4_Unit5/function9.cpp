#include "iostream"
#include "string"

using namespace std;
/*
 * ��дһ��ʹ��Ƕ��ѭ���ĳ���Ҫ���û�����һ��ֵ��ָ��Ҫ��ʾ�����С�Ȼ�󣬳�����ʾ��Ӧ������
 * �Ǻţ������е�һ�а���һ���Ǻţ��ڶ��а��������Ǻţ��Դ����ơ�ÿһ�а������ַ��������û�ָ��
 * �����������ǺŲ���������£����Ǻ�ǰ����Ͼ�㡣�ڳ��������������£�
 * Enter number of rows: 5
 * ....*
 * ...**
 * ..***
 * .****
 * *****
 */
int main9() {
    int num = 0;
    cout << "Enter number of rows: ";
    cin >> num;

    for (int i = 0; i < num; ++i) {
        for (int k = i; k < num-1; ++k) {
            cout << ".";
        }
        for (int j = num - i; j < num+1; ++j) {
            cout << "*";
        }
        cout << endl;
    }
    return 0;
}