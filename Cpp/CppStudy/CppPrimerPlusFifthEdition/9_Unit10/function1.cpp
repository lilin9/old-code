#include <iostream>

using namespace std;

/*
    定义一个类来表示银行账户。数据成员包括储户姓名、账户（使用字符串）和存款。成员函数执行
    如下操作：
    - 创建对象，并将其初始化。
    - 显示储户姓名、账户和存款。
    - 存入参数指定的存款。
    - 取出参数指定的款项
    请提供类声明，和方法实现。
*/

class Account {
    private:
        string name;
        string number;
        double deposit;
    public:
        void setAccount();
        void showAccount() const;
        void enter(double money);
        void out(double money);
};

void Account::setAccount() {
    this->name = "tom",
    this->number = "1234567890123456";
    this->deposit = 100000.00;
}

void Account::showAccount() const {
    cout << "name: " << this->name << endl;
    cout << "number: " << this->number << endl;
    cout << "deposit: " << this->deposit << endl;
}

void Account::enter(double money) {
    this->deposit += money;
}
    

void Account::out(double money) {
    this->deposit -= money;
}
    

int main1() {
    Account account;
    account.setAccount();
    account.showAccount();
    account.enter(999.99);
    cout << endl;
    account.showAccount();
    account.out(100.00);
    cout << endl;
    account.showAccount();
    return 0;
}
