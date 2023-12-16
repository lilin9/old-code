#include "iostream"
#include <cstring>

using namespace std;

/*
 * CandyBar 结构包含3个成员。第一个成员储存candy bar的品牌名称；第二个成员储存candy bar的重量（可能
 * 有小数）；第三个成员储存candy bar的热量（整数）。请编写一个程序，它使用一个这样的函数，即将CandyBar
 * 的引用、char指针、double和int作为参数，并用最后3个值设置相应的结构成员。最后3个参数的默认值分别是
 * “Millennium Munch”、2.85和350.另外，该程序还包含一个以CandyBar的引用为参数，并显示结构内容的函数。
 * 请尽可能地使用const。
 */
const int SIZE = 20;

struct CandyBar {
    char brand[SIZE];
    double weight;
    int heat;
};

void setCandyBar(CandyBar &candyBar,
          char *brand = "Millennium Munch",
          double weight = 2.85,
          int heat = 350) {
    strcpy(candyBar.brand, brand);
    candyBar.weight = weight;
    candyBar.heat = heat;
}

void showCandyBar(const CandyBar &candyBar) {
    cout << "Brand : " << candyBar.brand << endl;
    cout << "weight: " << candyBar.weight << endl;
    cout << "heat  : " << candyBar.heat << endl;
}

int main2() {
    CandyBar candyBar;
    setCandyBar(candyBar);
    showCandyBar(candyBar);

    setCandyBar(candyBar, "none", 3.33, 100);
    showCandyBar(candyBar);

    return 0;
}