#include "iostream"
#include "set"

using namespace std;

template<class T>
void printf(T& s) {
    for (auto it: s) {
        cout << it << " ";
    }
    cout << endl;
}

//������
void test1() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);

    printf<set<int>>(s1);
}

//��С�ͽ���
void test2() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);
    printf<set<int>>(s1);

    cout << "s1�Ĵ�С "<< s1.size() << endl;

    if (s1.empty()) {
        cout << "s1Ϊ��" << endl;
    } else {
        cout << "s1��Ϊ��" << endl;
    }

    set<int> s2;
    s2.swap(s1);
    cout << "s1: ";
    printf<set<int>>(s1);
    cout << "\ns2: ";
    printf<set<int>>(s2);
}

//��ѯ��ͳ��
void test3() {
    set<int> s1;
    s1.insert(10);
    s1.insert(30);
    s1.insert(20);
    s1.insert(50);
    s1.insert(40);
    printf<set<int>>(s1);

    auto it = s1.find(70);
    if (it == s1.end()) {
        cout << "s1����û��Ҫ���ҵ�����" << endl;
    } else {
        cout << "Ҫ�������ݵ�λ���� " << &it << endl;
    }

    cout << "s1��70�ĸ�����" << s1.count(70) << endl;
}

//multiset �� set ������
//multiset ���Բ����ظ���ֵ; set �����Բ����ظ���ֵ
void test4() {
    multiset<int> ms;
    ms.insert(10);
    ms.insert(10);
    ms.insert(40);
    ms.insert(40);
    ms.insert(20);
    ms.insert(30);
    ms.insert(50);

    printf<multiset<int>>(ms);
}

int main() {
//    test1();
//    test2();
//    test3();
    test4();
    return 0;
}