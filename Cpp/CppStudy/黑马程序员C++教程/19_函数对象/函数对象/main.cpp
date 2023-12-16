#include "iostream"
#include "cstring"

using namespace std;

class MyCompare {
public:
    int count;
    MyCompare() {
        this->count = 0;
    }
    void operator() (const string& text) {
        cout << text << endl;
        this->count ++;
    }
};

void test1() {
    MyCompare my;

    my("hello");
    my("hello");
    my("hello");
    my("hello");

    cout << "MyCompare调用的次数: " << my.count << endl;
}

int main() {
    test1();
    return 0;
}