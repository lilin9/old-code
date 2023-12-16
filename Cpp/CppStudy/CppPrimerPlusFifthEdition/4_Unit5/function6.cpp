#include "iostream"

using namespace std;
/*
 * ���һ����Ϊ car �Ľṹ���������������й���������Ϣ�������̣��������ֽ������ string �����е��ַ�������
 * ������ݣ�����������дһ���������û�ѯ���ж�������������󣬳���ʹ�� new ������һ������Ӧ������ car
 * �ṹ��ɵĶ�̬���顣��������������ʾ�û�����ÿ�����������̣������ɶ��������ɣ��������Ϣ����ע�⣬
 * ����Ҫ�ر�С�ģ���Ϊ���������ȡ��ֵ���ַ�������󣬳�����ʾÿ���ṹ�����ݡ��ó�������������£�
 * How many cars do you wish to catalog? 2
 * Car #1:
 * Please enter the make: Hudson Hornet
 * Please enter the year made: 1952
 * Car #2:
 * Please enter make: Kaiser
 * Please enter the year made: 1951
 * Here is your collection:
 * 1952 Hudson Hornet
 * 1951 Kaiser
 */

struct Car {
    string manufacturer;
    int yearMade;
};

int main6() {
    int number = 0;
    cout << "How many cars do you wish to catalog?";
    cin >> number;

    Car *carArray = new Car[number];
    for (int i = 0; i < number; ++i) {
        Car car;

        cout << "Car #" << i+1 << endl;
        cout << "Please enter make: ";
        cin.ignore();
        getline(cin, car.manufacturer);
        cout << "Please enter the year made: ";
        cin >> car.yearMade;

        carArray[i] = car;
    }

    cout << "Here is your collection:" << endl;
    for (int i = 0; i < number; ++i) {
        cout << carArray[i].yearMade << " " << carArray[i].manufacturer << endl;
    }
    return 0;
}