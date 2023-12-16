#include "iostream"
#include "vector"
#include "algorithm"

using namespace std;
/**
 * ���
 * 1. ���� bool ���͵ķº�����Ϊν��
 * 2. ��� operator() ����һ����������ô����һԪν��
 * 3. ��� operator() ����������������ô������Ԫν��
 */


//һԪν��
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

    //���� vector �д���5 �����ĸ���
    GreaterFive gf;
    auto it = find_if(v.begin(), v.end(), gf);
    if (it == v.end()) {
        cout << "û�д��� 5 ����" << endl;
    } else {
        cout << *it << endl;
    }
}

int main() {
test1();
    return 0;
}