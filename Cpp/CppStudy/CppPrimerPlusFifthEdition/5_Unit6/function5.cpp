#include "iostream"
#include "cctype"

using namespace std;

/*
 * 在 Neutronia 王国，货币单位是 tvarp，收入所得税的计算方式如下：
 * 5000 tvarps：不收税
 * 5001-15000 tvarps：10%
 * 15001-35000 tvarps：15%
 * 35000 tvarps 以上：20%
 * 例如，某人的收入为 38000 tvarps，则其所得税为 5000 x 0.00 + 10000 x 0.10 + 20000 x 0.15 +
 * 3000 x 0.20，即4600 tvarps。请编写一个程序，使用循环来要求用户输入收入，并报告所得税。当用户
 * 输入负数或非数字时，循环将结束。
 */

bool isNumber(const string& val) {
    bool result = true;
    for (auto item: val) {
        if (item == '.') {
            continue;
        }
        if (!isdigit(item)) {
            result = false;
        }
    }
    return result;
}

int main5() {
    string input;
    float income = 0;

    cout << "Enter your income: ";
    cin >> input;

    while (isNumber(input) && stof(input) >= 0) {
        income = stof(input);
        float tax = 0;
        if (income <= 5000) {
            tax = 0;
        } else if (income <= 15000) {
            tax = 5000*0 + (income-5000) * 0.1;
        } else if (income <= 35000) {
            tax = 5000 * 0 + 10000 * 0.10 + (income - 15000) * 0.15;
        } else {
            tax = 5000 * 0 + 10000 * 0.10 + 20000 * 0.15 + (income - 20000 - 10000 - 5000) * 0.20;
        }

        cout << "Your individual income tax is " << tax << " tvarps" << endl;

        cout << "Enter your income: ";
        cin >> input;
    }
    return 0;
}