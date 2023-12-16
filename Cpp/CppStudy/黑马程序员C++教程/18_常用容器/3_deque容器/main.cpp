#include "iostream"
#include "deque"
#include "algorithm"

using namespace std;

void printf(const deque<int>& de) {
    for (auto it = de.begin(); it != de.end(); it++) {
        cout << *it << " ";
    }
    cout << endl;
}

//deque������
void test1() {
    deque<int> d1;

    for (int i = 0; i < 100; i += 10) {
        d1.push_back(i);
    }
    printf(d1);

    deque<int> d2(d1.begin(), d1.end());
    printf(d2);

    deque<int> d3(10, 100);
    printf(d3);

    deque<int> d4(d1);
    printf(d4);
}

//��С����
void test2() {
    deque<int> d1;

    for (int i = 0; i < 100; i += 10) {
        d1.push_back(i);
    }

    if (d1.empty()) {
        cout << "û��ֵ" << endl;
    } else {
        cout << "��ֵ" << endl;
    }

    cout << "d1��Ԫ�ظ���: " << d1.size() << endl;

    d1.resize(20, -1);
    printf(d1);
}

//����
void test3() {
    deque<int> d1;
    d1.push_front(10);
    d1.push_front(40);
    d1.push_front(30);
    d1.push_back(100);
    d1.push_back(600);
    d1.push_back(300);

    printf(d1);

    sort(d1.begin(), d1.end());
    printf(d1);
}

int main() {
//    test1();
//    test2();
    test3();
    return 0;
}