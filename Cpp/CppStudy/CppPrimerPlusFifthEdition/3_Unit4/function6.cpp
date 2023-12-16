#include "iostream"

using namespace std;
/*
 * 结构 CandyBar 包含 3 个成员，如编程练习 5 所示。请编写一个程序，创建一个包含 3 个元素的 CandyBar
 * 数组，并将它们初始化为所选择的值，然后显示每个结构的内容。
 */

struct CandyBar {
    string band;
    float weight;
    int calories;
};

int main6() {
    CandyBar candyBar1{"Mocha Munch", 2.3, 350};
    CandyBar candyBar2{"Chocolate", 4.3, 400};
    CandyBar candyBar3{"Cookies", 1.3, 150};

    CandyBar arrays[]{candyBar1, candyBar2, candyBar3};

    for(const CandyBar& item: arrays) {
        cout << "Band: " << item.band << " Weight: " << item.weight
             << " Calories: " << item.calories << endl;
    }
    return 0;
}