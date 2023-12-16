#include "iostream"

using namespace std;

/*
 * 设计一个名为 calculate() 的函数，它接受两个 double 值和一个指向函数的指针，而被指向的函数
 * 接受两个 double 参数，并返回一个 double 值。calculate() 函数的类型也是 double，并返回被指
 * 向的函数使用 calculate() 的两个 double 参数计算得到的值。例如，假设 add() 函数的定义如下：
 * double add(double x, double y) {
 *      return x + y;
 * }
 * 则下述代码中的函数调用：
 * double q = calculate(2.5, 10.4, add);
 * 将导致 calculate() 把 2.5 和 10.4 传递给 add() 函数，并返回 add() 的返回值 (12.9)。
 * 请编写一个程序，它调用上述两个函数和至少另一个与 add() 类似的函数。该程序使用循环来让用户
 * 成对的输入数字。对于每对数字，程序都使用 calculate() 来调用 add() 和至少一个其他的函数。
 * 如果读者爱冒险，可以尝试创建一个指针数组，其中的指针指向 add() 样式的函数，并编写一个循环，
 * 使用这些指针连续让 calculate() 调用这些函数。提示：下面是声明这种指针数组的方式，其中包含 3
 * 个指针：
 * double (*pf[3]) (double, double);
 * 可以采用数组初始化句法，并将函数名作为地址来初始化这样的数组。
 */

double add(double x, double y) {
    return x + y;
}

double reduce(double x, double y) {
    return x - y;
}

double multi(double x, double y) {
    return x * y;
}

double remove(double x, double y) {
    if (y == 0) return 0;
    return x / y;
}

double calculate(double x, double y, double (*funcArr)(double, double)) {
    return funcArr(x, y);
}

int main9() {
    double (*funcArr[4]) (double, double) = {add, multi, remove, reduce};
    double x, y = 0;
    cout << "Enter x and y (q to quit): ";
    while (cin >> x >> y) {
        for(auto func: funcArr) {
            cout << calculate(x, y, func) << endl;
        }
        cout << "Enter x and y (q to quit): ";
    }

    return 0;
}