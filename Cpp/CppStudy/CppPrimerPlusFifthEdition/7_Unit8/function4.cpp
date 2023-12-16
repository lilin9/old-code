#include "iostream"
#include "cstring"

using namespace std;

/*
 * 下面是一个程序框架：
 * #include <iostream>
 * using namespace std;
 * #include <cstring>   //for strlen(), strcpy()
 * struct stringy {
 *      char * str; //points to a string
 *      int ct;     //length of string(not counting '\0')
 * };
 *
 * //prototypes for set(). show(). and show() go here
 * int main() {
 *      stringy beany;
 *      char testing[] = "Reality isn't what it used to be.";
 *      set (beany, testing);   //first argument is a reference
 *              //allocates space to hold copy of testing
 *              //sets str member of beany to point to the
 *              //new block, copies testing to new block.
 *              //and sets ct member of beany
 *      show(beany);        //prints member string once
 *      show(beany, 2);     //prints member string twice
 *      testing[0] = 'D';
 *      testing[1] = 'u';
 *      show(testing);      //prints testing string once
 *      show(testing, 3);   //prints testing string thrice
 *      show("Done!");
 *      return 0;
 * }
 * 请提供其中描述的函数和原型，从而完成该程序。注意，应有两个show()函数，每个都使用默认参数，
 * 请尽可能使用const参数。set()使用new分配足够的空间来储存指定的字符串。这里使用的技术和设计
 * 和实现类时使用的相似。（可能还必须修改头文件的名称，删除using编译指令，这取决于所用的编译
 * 器。）
 */

struct Stringy {
    char *str;
    int ct;
};

void set(Stringy &stringy, char str[]) {
    stringy.ct = strlen(str) + 1;
    stringy.str = new char[stringy.ct];
    stringy.str = str;
}

void show(Stringy stringy, int count = 1) {
    for (int i = 0; i < count; ++i) {
        cout << stringy.str << endl;
    }
}

void show(char str[], int count = 1) {
    for (int i = 0; i < count; ++i) {
        cout << str << endl;
    }
}

int main4() {
    Stringy beany;
    char testing[] = "Reality isn't what it used to be.";
    set (beany, testing);
    show(beany);
    cout << endl;
    show(beany, 2);
    cout << endl;
    testing[0] = 'D';
    testing[1] = 'u';
    show(testing);
    cout << endl;
    show(testing, 3);
    cout << endl;
    show("Done!");
    return 0;
}