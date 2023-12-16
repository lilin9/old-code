#include "iostream"
#include "vector"

using namespace std;

void printVector(vector<int>& v) {

    for (int& it : v) {
        cout << it << " ";
    }
    cout << endl;
}

//vector容器构造函数
void test1() {
    vector<int> vec = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    printVector(vec);
}

//赋值
void test2() {
    vector<int> v1;
    for (int i = 0; i < 10; ++i) {
        v1.push_back(i);
    }
    printVector(v1);

    //赋值
    vector<int> v2;
    v2 = v1;
    printVector(v2);

    //assign
    vector<int> v3;
    v3.assign(v1.begin(), v1.end());
    printVector(v3);

    //n 个 elem 方式赋值
    vector<int> v4;
    v4.assign(10, 100);
    printVector(v4);
}

//容器的大小和容量
void test3() {
    vector<int> v1;
    for (int i = 0; i < 10; ++i) {
        v1.push_back(i);
    }

    cout << v1.empty() << endl;
    cout << "v1的容量: " << v1.capacity() << endl;
    cout << "v1的大小: " << v1.size() << endl;

    //重新指定大小
    v1.resize(v1.size()/2);
    printVector(v1);
}

//预留空间  容器预留 len 个元素长度，预留位置不可以初始化，元素不可访问
void test4() {
    vector<int> v;

    //预先预留空间
    v.reserve(10000);

    int* temp = NULL;
    int count = 0;
    for (int i = 0; i < 10000; ++i) {
        v.push_back(i);

        //判断在给容器赋值过程中，程序开辟了多少次内存空间
        if (temp != &v[0]) {
            temp = &v[0];
            count ++;
        }
    }

    cout << "代码一共开辟了 " << count << "次内存空间" << endl;
}


int main() {
    test4();
    return 0;

}