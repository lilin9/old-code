#include "iostream"
#include "fstream"

using namespace std;

/*
 * 完成编程练习 function6.cpp，但从文件中读取所需的信息。该文件的第一项应为捐款人数，余下的内容应为成
 * 对的行。在每一对中，第一行为捐款人姓名，第二行为捐款数额。即该文件应类似于下面所示：
 * 4
 * Sam Stone
 * 2000
 * Freida Flass
 * 100500
 * Tammy Tubbs
 * 5000
 * Rich Raptor
 * 55000
 */

struct DonorPerson {
    string name;
    double amount;
};

int main9() {
    //读文件对象
    ifstream fin;

    //打开文件
    fin.open(R"(D:\Programming\Cpp\CppStudy\CppPrimerPlusFifthEdition\5_Unit6\text2.txt)",
             ios::in);

    //判断文件是不是打开了
    if (!fin.is_open()) {
        return 0;
    }

    //读取到的一行内容
    string line;
    //用来计数，记录读取哪一行了
    int counter = 1;
    //记录添加到 grandPatrons 的个数
    int grandCount = 0;
    //记录添加到 patrons 的个数
    int patronCount = 0;
    //创建两个对象数组
    DonorPerson *grandPatrons;
    DonorPerson *patrons;
    //记录读取到的姓名
    string name;

    while (getline(fin, line)) {
        if (counter == 1) {
            grandPatrons = new DonorPerson[stoi(line)];
            patrons = new DonorPerson[stoi(line)];
            counter++;
            continue;
        }

        if (counter % 2 == 0) {
            name = line;
        } else {
            DonorPerson person;
            person.name = name;
            person.amount = stod(line);

            if (person.amount >= 10000) {
                grandPatrons[grandCount] = person;
                grandCount++;
            } else {
                patrons[patronCount] = person;
                patronCount++;
            }
        }
        counter++;
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

    //释放为两个对象数组分配的空间
    delete [] grandPatrons;
    delete [] patrons;
    return 0;
}