#include "iostream"

using namespace std;
/*
 * ��ɱ����ϰ function4.cpp������һ��ʹ��һ����ά�������������롪��3����ÿ���µ������������򽫱���
 * ÿ����������Լ�3�������������
 */

int main5() {
    int sales[3][12];

    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 12; ++j) {
            sales[i][j] = i * j + 10;
        }
    }

    int sumSalesForMonth = 0;
    int sumSalesForYear = 0;
    for (int i = 0; i < 3; ++i) {
        for (int j = 0; j < 12; ++j) {
            sumSalesForMonth += sales[i][j];
            sumSalesForYear += sales[i][j];
        }
        cout << "ͼ���" << i << "����������ǣ�" << sumSalesForMonth << endl;
        sumSalesForMonth = 0;
    }
    cout << "ͼ����������������ǣ�" << sumSalesForYear << endl;
    return 0;
}