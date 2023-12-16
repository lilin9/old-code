#include "iostream"
#include "vector"
#include "algorithm"

using namespace std;
/**
 * 概念：
 * 1. 返回 bool 类型的仿函数称为谓词
 * 2. 如果 operator() 接收一个参数，那么叫做一元谓词
 * 3. 如果 operator() 接收两个参数，那么叫做二元谓词
 */


//一元谓词
class GreaterFive {
public:
    bool operator() (int v) {
        return v > 5;
    }
};

void test1() {
    vector<int> v;

    v.reserve(10);
    for (int i = 0; i < 10; ++i) {
        v.push_back(i);
    }

    //查找 vector 中大于5 的数的个数
    GreaterFive gf;
    auto it = find_if(v.begin(), v.end(), gf);
    if (it == v.end()) {
        cout << "没有大于 5 的数" << endl;
    } else {
        cout << *it << endl;
    }
}

int main() {
test1();
    return 0;
}