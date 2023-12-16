#include <iostream>

using namespace std;

//菱形继承问题

//动物类
class Animal {
    public:
    int age;
};

//羊类 
//virtual: 虚继承，解决菱形继承问题
class Sheep: virtual public Animal {};

//驼类
class Camel: virtual public Animal {};

//羊驼类
class Aplaca: public Sheep, public Camel {};

int main13() {
   Aplaca aplaca;

   aplaca.Camel::age = 10;
   aplaca.Sheep::age = 20;

   cout << aplaca.Camel::age << endl;
   cout << aplaca.Sheep::age << endl;
   cout << aplaca.age << endl;
   return 0;
}
