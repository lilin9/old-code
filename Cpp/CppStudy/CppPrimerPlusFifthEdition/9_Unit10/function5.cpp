#include <iostream>
#include "string.h"
using namespace std;

/*
    考虑下面的结构声明：
    struct customer {
        char fullname[35];
        double payment;
    };
    编写一个程序，它从堆栈中添加和删除customer结构（堆栈用Stack类声明表示）。每次customer结构被删除时，
    其payment的值都被加入到总数中，并报告总数。注意：应该可以直接使用Stack类而不作修改；只需修改typeof
    声明，使Item的类型为customer，而不是unsigned long即可。
*/

struct Customer {
    char fullname[35];
    double payment;
};

class Stack {
   public:
    Customer *item;
    double sum;

    void add(Customer *customer) {
        this->item = customer;
    }

    void del() {
        this->sum += this->item->payment;
        cout << "The current sum is " << this->sum << endl;
        
        delete this->item;
        this->item = NULL;
    }
};

static Stack stack;

int main5() {
    Customer *customer = new Customer;
    strcpy(customer->fullname, "tom");
    customer->payment = 100.00;
    Customer *customer1 = new Customer;
    strcpy(customer1->fullname, "tony");
    customer1->payment = 200.00;
    Customer *customer2 = new Customer;
    strcpy(customer2->fullname, "jerry");
    customer2->payment = 500.00;

    stack.add(customer);
    stack.del();
    stack.add(customer1);
    stack.del();
    stack.add(customer2);
    stack.del();
    return 0;
}
