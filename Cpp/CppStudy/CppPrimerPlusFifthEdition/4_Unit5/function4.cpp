#include "iostream"

using namespace std;
/*
 * ����Ҫ���� C++ For Fools һ�顣���дһ����������ȫ����ÿ���µ���������ͼ�����������������۶��
 * ����ͨ��ѭ����ʹ�ó�ʼ��Ϊ�·��ַ��� �� char *���飨�� string �������飩���½�����ʾ�����������
 * ���ݴ�����һ�� int �����С�Ȼ�󣬳�����������и�Ԫ�ص���������������һ������������
 */

int main4() {
    string month[12];
    int sales[12];
    int number = 0;
    for (int i = 0; i < 12; ++i) {
        month[i] = to_string(i+1) + "�·�";
        cout << "��C++ For Fools��" << month[i] << "���������ǣ�";
        cin >> number;
        sales[i] = number;
    }

    int sum = 0;
    for (int i = 0; i < 12; ++i) {
        sum += sales[i];
    }

    cout << "��C++ For Fools��" << month[0] << "��" << month[11] << "������������" << sum << "��" << endl;

    return 0;
}