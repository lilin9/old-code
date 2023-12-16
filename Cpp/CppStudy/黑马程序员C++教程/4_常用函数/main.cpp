#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

int main4() {
    // 设置随机数种子
    srand(time(NULL));
    for (size_t i = 0; i < 10; i++) {
        cout << "随机数: " << rand() << endl;
    }

    return 0;
}
