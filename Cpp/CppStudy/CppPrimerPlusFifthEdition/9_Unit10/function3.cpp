#include <iostream>
#include "cstring"

using namespace std;

/*
    完成第9章的编程练习1，但是要用正确的golf类声明替换那里的代码。用带合适参数的构造函数替换
    setgolf(golf &, const char , int)，以提供初始值。保留setgolf()的交互版本，但要用构造
    函数来实现它（例如，setgolf()的代码应该获得数据，将数据传递给构造函数来创建一个临时对象，
    并将其赋给调用对象，即*this）
*/

const int Len = 40;
class golf {
   private:
    char fullname[Len];
    int handicap;

   public:
    golf(const char* name, int hc) {
        strncpy(fullname, name, Len - 1);
        fullname[Len - 1] = '\0';
        handicap = hc;
    }

    golf() {
        fullname[0] = '\0';
        handicap = 0;
    }

    void set_handicap(int hc) {
        handicap = hc;
    }

    void show() const {
        std::cout << "Name: " << fullname << std::endl
                  << "Handicap: " << handicap << std::endl;
    }
};

void setgolf(golf& g) {
    char name[g.Len];
    int handicap;

    std::cout << "Enter name: ";
    std::cin.getline(name, g.Len);

    std::cout << "Enter handicap: ";
    std::cin >> handicap;

    // remove remaining input
    while (std::cin.get() != '\n')
        continue;

    // construct a temporary object using the constructor
    golf temp(name, handicap);

    // assign the temporary object to the calling object
    g = temp;
}

void setgolf(golf& g, const char* name, int hc) {
    // construct a temporary object using the constructor
    golf temp(name, hc);

    // assign the temporary object to the calling object
    g = temp;
}

int main3() {
    golf g1;
    setgolf(g1);
    g1.show();

    golf g2;
    setgolf(g2, "John Doe", 10);
    g2.show();

    return 0;
}
