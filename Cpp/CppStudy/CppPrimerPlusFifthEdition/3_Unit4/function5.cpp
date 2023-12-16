#include "iostream"

using namespace std;
/*
 * 结构 CandyBar 包含 3 个成员。第一个成员储存了糖块的品牌；第二个成员储存了糖块的重量（可以有小数），
 * 第三个成员储存了糖块的卡路里含量（整数）。请编写一个程序，声明这个结构，创建一个名为 snack 的
 * CandyBar 遍历，并将其成员分别初始化为”Mocha Munch“、2.3 和 350。初始化应该在声明 snack 时进行。
 * 最后，程序显示 snack 变量的内容。
 */

struct CandyBar {
    string band;
    float weight;
    int calories;
};

int main5() {
    CandyBar snack{"Mocha Munch", 2.3, 350};

    cout << "Band: " << snack.band << "\nWeight: " << snack.weight
    << "\nCalories: " << snack.calories << endl;
    return 0;
}