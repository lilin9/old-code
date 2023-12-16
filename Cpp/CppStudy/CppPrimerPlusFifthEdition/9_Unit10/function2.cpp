#include <iostream>
#include "string.h"

using namespace std;

/*
    下面是一个非常简单的类定义：
    class Person {
    private:
        static const LIMIT = 25;
        string lname;       //Person's last name
        char fname[LIMIT];  //Person's first name
    public:
        Person() {lname = ""; fname[0] = "";}
        Person(const string &ln, const char *fn = "Heyyou"); //#2
        //the following methods display lname and fname
        void Show() const;          //firstname lastname format
        void FormalShow() const;    //lastname, firstname format
    };

    通过提供未定义的方法的代码来完成这个类的实现。然后编写一个使用这个类的程序，它使用了
    三种可能的构造函数调用（没有参数、一个参数和两个参数）以及两种显示方法。下面是一个使用
    这些构造函数和方法的例子：
    Person one;                 //use default constructor
    Person two("Smythecraft");  //use #2 with one default argument
    Person three("Dimwiddy", "Sam");    //use #2, no defaults
    one.Show();
    cout << endl;
    one.FormalShow();
    //etc. for two and three
*/
class Person {
   private:
    static const int LIMIT = 25;
    string lname;
    char fname[LIMIT];

   public:
    Person() {
        lname = "";
        fname[0] = ' ';
    }
    Person(const string& ln, const char* fn = "Heyyou");
    void Show() const;
    void FormalShow() const;
};

Person::Person(const string& ln, const char* fn) {
    this->lname = ln;
    strcpy(this->fname, fn);
}

void Person::Show() const {
    cout << this->lname << endl;
}

void Person::FormalShow() const {
    cout << this->fname << " " << this->lname << endl;
}

int main2() {
    Person one;                       // use default constructor
    Person two("Smythecraft");        // use #2 with one default argument
    Person three("Dimwiddy", "Sam");  // use #2, no defaults
    one.Show();
    cout << endl;
    one.FormalShow();
    
    cout << endl;

    two.Show();
    cout << endl;
    two.FormalShow();

    cout << endl;

    three.Show();
    cout << endl;
    three.FormalShow();
    // etc. for two and three
    return 0;
}
