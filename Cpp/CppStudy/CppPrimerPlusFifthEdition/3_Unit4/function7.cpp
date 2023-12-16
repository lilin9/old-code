#include "iostream"

using namespace std;
/*
 * William Wingate 从事披萨饼分析服务。对比每个披萨饼，他都需要记录下列信息：
 * - 披萨饼公司的名称，可以有多个单词组成。
 * - 披萨饼的直径。
 * - 披萨饼的重量。
 * 请设计一个能够储存这些信息的结构，并编写一个使用这种结构变量的程序。程序将请求用户输入上述信息，
 * 然后显示这些信息。请使用 cin（或它的方法）和 cout。
 */
struct Pizza {
    string companyName;
    float dia;
    float weight;
};

int main7() {
    Pizza pizza;

    cout << "Enter name of Pizza company: ";
    getline(cin, pizza.companyName);
    cout << "Enter diameter of Pizza: ";
    cin >> pizza.dia;
    cout << "Enter weight of Pizza: ";
    cin >> pizza.weight;

    cout << "According your input, the name of the pizza company is [" << pizza.companyName
    << "] , \nits diameter is [" << pizza.dia << "]cm and weight is [" << pizza.weight << "]kg.";
    return 0;
}