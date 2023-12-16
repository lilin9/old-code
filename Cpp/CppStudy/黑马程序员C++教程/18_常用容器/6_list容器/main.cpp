#include "iostream"
#include "list"

using namespace std;

void printf(const list<int>& L) {
    for (auto i: L) {
        cout << i << " ";
    }
    cout << endl;
}

//构造函数
void test1() {
    list<int> L1;
    L1.push_back(10);
    L1.push_back(20);
    L1.push_back(30);
    L1.push_back(40);
    L1.push_back(50);

    printf(L1);
}

bool myCompare(int v1, int v2) {
    return v1 > v2;
}

//反转和排序
void test2() {
    list<int> L1;
    L1.push_back(10);
    L1.push_back(60);
    L1.push_back(50);
    L1.push_back(30);
    L1.push_back(70);
    printf(L1);

    L1.reverse();
    printf(L1);

    //升序排序
//    L1.sort();
//    printf(L1);

    //降序排序
    L1.sort(myCompare);
    printf(L1);
}

int main() {
//    test1();
    test2();
    return 0;
}