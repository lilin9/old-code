#include "iostream"
#include "cctype"

using namespace std;

/*
 * 编写一个程序，记录捐助给“维护合法权利团体”的钱。该程序要求用户输入捐献者数目，然后要求
 * 用户输入每一个捐献者的姓名和款项。这些信息被储存在一个动态分配的结构数组中。每个结构有两个成员：
 * 用来储存姓名的字符数组（或string对象）和用来储存款项的double成员。读取所有数据后，程序将显示
 * 所有捐款超过 10000 的捐款者的姓名和其捐款数额。该列表前应包含一个标题，指出下面的捐款者是重要
 * 捐款人（Grand Patrons）。然后，程序将列出其他的捐款者，该列表要以Patrons开头。如果某种类别没有
 * 捐款者，则程序将打印单词“none”。该程序只显示这两种类别，而不进行排序。
 */

struct DonorPerson {
    string name;
    double amount;
};

int main6() {
    int personCount = 0;
    cout << "Enter number of donors: ";
    cin >> personCount;

    DonorPerson grandPatrons[personCount];
    DonorPerson patrons[personCount];
    int grandCount = 0;
    int patronCount = 0;
    for (int i = 0; i < personCount; ++i) {
        DonorPerson person;
        cout << "Please enter the name of the " << i+1 << " donor: ";
        cin >> person.name;
        cout << "Please enter the amount of the " << i+1 << " donor: ";
        cin >> person.amount;
        cout << endl;

        if (person.amount >= 10000) {
            grandPatrons[i] = person;
            grandCount++;
        } else {
            patrons[i] = person;
            patronCount++;
        }
    }

    cout << "Grand Patrons: " << endl;
    if (grandCount != 0) {
        for (int i = 0; i < grandCount; ++i) {
            cout << "   " << grandPatrons[i].name << ", " << grandPatrons[i].amount << endl;
        }
    } else {
        cout << "   " << "none" << endl;
    }

    cout << "Patrons: " << endl;
    if (patronCount != 0) {
        for (int i = 0; i < patronCount; ++i) {
            cout << "   " << patrons[i].name << ", " << patrons[i].amount << endl;
        }
    } else {
        cout << "   " << "none" << endl;
    }

    return 0;
}