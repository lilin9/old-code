#include "iostream"

using namespace std;

/*
 * 许多州的彩票发行机构都使用如程序清单7.4所示的简单彩票玩法的变体。在这些玩法中，玩家从一组
 * 被称为域号码（field number）的号码中选择几个。例如，可以从域号码1~47中选择5个号码；还可以从
 * 第二个区间（如1~27）中选择一个号码（成为特选号码）。要赢得头奖，必须正确猜中所有的号码。中头
 * 奖的几率是选中所有域号码的几率与选中特选号码的几率的乘积。例如，在这个例子中，中头奖的几率
 * 是从47个号码中正确选取5个号码的几率与从27个号码中正确选择1个号码的几率的乘积。请修改程序清
 * 单7.4，以计算中得这种彩票头奖的几率。
 */

long double probability(unsigned numbers, unsigned picks) {
    long double result = 1.0;
    long double n;
    unsigned p;

    for (n = numbers, p = picks; p > 0; --p, --n)  {
        result = result * n / p;
    }
    return result;
}

int main4() {
    double total1, choices1, total2, choices2;
    cout << "Enter the total number of choices on the game card and the number of picks allowed: \n";

    while ((cin >> total1 >> choices1 >> total2 >> choices2) &&
            (total1 >= choices1 && total2 >= choices2)) {
        cout << "You have on change in ";
        cout << probability(total1, choices1) * probability(total2, choices2);
        cout << "of winning." << endl;
        cout << "Next four numbers(q to quit): ";
    }
    cout << "\nbye!\n";
    return 0;
}