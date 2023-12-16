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
    q.push(Person("唐三藏"));
    q.push(Person("孙悟空"));
    q.push(Person("猪悟能"));
    q.push(Person("沙悟净"));
    q.push(Person("白龙马"));

    cout << "队列中的元素个数: " << q.size() << endl << endl;
    while (!q.empty()) {
        cout << "队头元素: " << q.front().name << endl;
        cout << "队尾元素: " << q.back().name << endl;
        q.pop();
    }
    cout << "\n\n队列中的元素个数: " << q.size() << endl;
}

int main() {
    test1();
    return 0;
}