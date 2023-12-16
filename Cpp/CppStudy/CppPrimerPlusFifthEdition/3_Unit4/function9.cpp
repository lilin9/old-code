#include "iostream"

using namespace std;
/*
 * 完成编程练习 function6.cpp，但使用 new 来动态分配数组，
 * 而不是声明一个包含 3 个元素的 CandyBar 数组。
 */
struct CandyBar {
    string band;
    float weight;
    int calories;
};

int main9() {
    CandyBar candyBar1{"Mocha Munch", 2.3, 350};
    CandyBar candyBar2{"Chocolate", 4.3, 400};
    CandyBar candyBar3{"Cookies", 1.3, 150};

    CandyBar *array = new CandyBar[3];
    array[0] = candyBar1;
    array[1] = candyBar2;
    array[2] = candyBar3;

    for (int i = 0; i < 3; ++i) {
        cout << "Band: " << array[i].band << " Weight: " << array[i].weight
             << " Calories: " << array[i].calories << endl;
    }

    delete [] array;
    return 0;
}