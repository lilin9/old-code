#include "iostream"

using namespace std;
/*
 * Daphne 以 10% 的单利投资了 100 美元。也就是说，每一年的利润都是投资额的 10%，即每年 10美元：
 * 利息 = 0.10 ×原始存款
 * 而 Cleo 以 5% 的复利投资了 100 美元。也就是说，利息是当前存款（包括获得的利息）的 5%；
 * 利息 = 0.05 x 当前存款
 * Cleo 在第一年投资 100 美元的盈利是 5%——得到了 105 美元。下一年的盈利是 105 美元的 5%——即
 * 5.25 美元，以此类推。请编写一个程序，计算多少年后，Cleo 的投资价值才能超过 Daphne 的投资价值，
 * 并显示此时两个人的投资价值
 */

const float beginMoney = 100.0;

float getDaphneMoney(float lastMoney) {
    return lastMoney + beginMoney * float(0.10);
}

float getCleoMoney(float lastMoney) {
    return lastMoney + lastMoney * float(0.05);
}

int main3() {
    //daphne 的存款
    float daphne = 100;
    //cleo 的存款
    float cleo = 100;
    int year = 1;

    while(true) {
        if (cleo > daphne) {
            break;
        }

        daphne += getDaphneMoney(daphne);
        cleo += getCleoMoney(cleo);

        year++;
    }

    cout << year << "年后，Cleo 的投资价值才能超过 Daphne 的投资价值" << endl;
    cout << "此时,Cleo 投资价值是：" << cleo << "，Daphne 的投资价值是：" << daphne << endl;
    return 0;
}