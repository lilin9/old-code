#include "iostream"
#include "queue"
#include "cstring"

using namespace std;

class Person {
public:
    string name;
    Person(string name) {
        this -> name = name;
    }
};

void test1() {
    queue<Person> q;
    q.push(Person("������"));
    q.push(Person("�����"));
    q.push(Person("������"));
    q.push(Person("ɳ��"));
    q.push(Person("������"));

    cout << "�����е�Ԫ�ظ���: " << q.size() << endl << endl;
    while (!q.empty()) {
        cout << "��ͷԪ��: " << q.front().name << endl;
        cout << "��βԪ��: " << q.back().name << endl;
        q.pop();
    }
    cout << "\n\n�����е�Ԫ�ظ���: " << q.size() << endl;
}

int main() {
    test1();
    return 0;
}