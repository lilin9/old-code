#include "iostream"
#include "map"

using namespace std;

void printf(map<int, int>& m) {
    for (auto it: m) {
        cout << "key = " << it.first << ", value = " << it.second << endl;
    }
}

//map���� �������͸�ֵ
void test1() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    printf(m);
}

//��С�ͽ���
void test2() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    cout << "m�Ĵ�С: " << m.size() << endl;

    cout << "m�Ƿ�Ϊ��: ";
    if (m.empty()) {
        cout << "Ϊ��" << endl;
    } else {
        cout << "��Ϊ��" << endl;
    }

    map<int, int> m2;
    m2.swap(m);

    cout << "m: " << endl;
    printf(m);
    cout << "\nm1: " << endl;
    printf(m2);
}

//���Һ�ͳ��
void test3() {
    map<int, int> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    cout << "key=1�����Ƿ����: " << m.find(1)->second << endl;
    cout << "ͳ�� key=1 �ĸ���: " << m.count(1) << endl;
}

class MyCompare {
public:
    bool operator() (const int v1, const int v2) const {
        return v1 > v2;
    }
};

//�Զ�������
void test4() {
    map<int, int, MyCompare> m;
    m.insert(pair<int, int>(1, 10));
    m.insert(pair<int, int>(2, 20));
    m.insert(pair<int, int>(3, 30));
    m.insert(pair<int, int>(4, 40));
    m.insert(pair<int, int>(5, 50));

    for (auto it: m) {
        cout << "key = " << it.first << ", value = " << it.second << endl;
    }
}

int main() {
//    test1();
//    test2();
//    test3();
    test4();
    return 0;
}