#include "iostream"
#include "stack"

using namespace std;

void printf(stack<int>& s) {
    while (!s.empty()) {
        cout << "ջ��Ԫ���� " << s.top() << endl;
        s.pop();
    }
    cout << endl;
}

//���캯��
void test1() {
    stack<int> s1;

    s1.push(10);
    s1.push(20);
    s1.push(30);
    s1.push(40);
    s1.push(50);

    printf(s1);
}

int main() {
    test1();
    return 0;
}