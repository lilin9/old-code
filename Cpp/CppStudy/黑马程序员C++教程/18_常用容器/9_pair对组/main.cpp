#include "iostream"
#include "cstring"

using namespace std;

int main() {
    //第一种创建方式
    pair<string, int> p1(string("tom"), 12);
    cout << "name: " << p1.first << ", age: " << p1.second << endl;

    //第二种
    pair<string, int> p2 = make_pair("tony", 13);
    cout << "name: " << p2.first << ", age: " << p2.second << endl;
    return 0;
}