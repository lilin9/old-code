#include "iostream"

using namespace std;

/*
 * 加入 Benevolent Order of Programmer 后，在 BOP 大会上，人们便可以通过加入者的真实姓名、头衔或
 * 秘密 BOP 姓名来了解他（她）。请编写一个程序，可以使用真实姓名、头衔、秘密姓名或成员偏好来列出
 * 成员。编写该程序时，请使用下面的结构：
 * //Benevolent Order of Programmers name structure
 * struct bop {
 *      char fullname[strsize];     //real name
 *      char title[strsize];        //job title
 *      char bopname[strsize];      //secret BOP name
 *      int preference;             //0 = fullname, 1 = title, 2 = bopname
 * }
 * 该程序创建一个由上述结构组成的小型数组，并将其初始化为适当的值。另外，该程序使用一个循环，让用户
 * 在下面的选项中进行选择：
 * a. display by name       b. display by title
 * c. display by bopname    d. display by preference
 * q. quit
 * 注意，“display by preference”并不意味着显示成员的偏好，而是意味着根据成员的偏好来列出成员。例如，
 * 如果偏好号为 1，则选择 d 将显示程序员的头衔。该程序的运行情况如下：
 * Benevolent Order of Programmers Report
 * a. display by name       b. display by title
 * c. display by bopname    d. display by preference
 * q. quit
 * Enter your choice: a
 * Wimp Macho
 * Raki Rhodes
 * Celia Laiter
 * Hoppy Hipman
 * Pat Hand
 * Next choice: d
 * Wimp Macho
 * Junior Programmer
 * MIPS
 * Analyst Trainee
 * LOOPY
 * Next choice: q
 * Bye!
 */

const int strSize = 20;

struct Bop {
          char fullName[strSize];     //real name
          char title[strSize];        //job title
          char bopName[strSize];      //secret BOP name
          int preference;             //0 = fullname, 1 = title, 2 = bopname
};

void displayByName(Bop (&bopArr)[5]) {
    for (auto & i : bopArr) {
        cout << i.fullName << endl;
    }
}

void displayByTitle(Bop (&bopArr)[5]) {
    for (auto & i : bopArr) {
        cout << i.title << endl;
    }
}

void displayByBopName(Bop (&bopArr)[5]) {
    for (auto & i : bopArr) {
        cout << i.bopName << endl;
    }
}

void displayByPreference(Bop (&bopArr)[5]) {
    for (auto & i : bopArr) {
        if (i.preference == 1) {
            cout << i.title << endl;
        } else {
            cout << i.bopName << endl;
        }
    }
}

int main4() {
    Bop bop1{"Wimp Macho", "Useless", "Wimp Macho", 2};
    Bop bop2{"Raki Rhodes", "Junior Programmer", "Will", 1};
    Bop bop3{"Celia Laiter", "MIPS", "CuteBoy", 1};
    Bop bop4{"Hoppy Hipman", "Analyst Trainee", "Ironman", 1};
    Bop bop5{"Pat Hand", "LOOPY", "Puppy", 1};

    Bop bopArr[5] = {bop1, bop2, bop3, bop4, bop5};

    cout << "a. display by name       b. display by title" << endl;
    cout << "c. display by bopname    d. display by preference" << endl;
    cout << "q. quit" << endl;

    char input;
    cout << "Enter your choice: ";
    cin >> input;

    while (input != 'q') {
        switch (input) {
            case 'a':
                displayByName(bopArr);
                break;
            case 'b':
                displayByTitle(bopArr);
                break;
            case 'c':
                displayByBopName(bopArr);
                break;
            case 'd':
                displayByPreference(bopArr);
                break;
            default:
                cout << "Please enter a c, p, t, or g." << endl;
                break;
        }
        cout << "Next choice: ";
        cin >> input;
    }

    cout << "Bye!" << endl;
    return 0;
}