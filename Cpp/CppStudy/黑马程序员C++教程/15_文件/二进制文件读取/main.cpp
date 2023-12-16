#include <iostream>
// 1.引入头文件
#include <fstream>

using namespace std;

class Person {
    public:
    char name[64];
    int age;
};

void write() {
    ofstream ofs;
    
    ofs.open("person.txt", ios::out | ios::binary);

    Person person = {"tom", 24};

    ofs.write((const char*) &person, sizeof(person));

    ofs.close();
}

void read() {
    ifstream ifs;

    ifs.open("person.txt", ios::in | ios::binary);

    if (!ifs.is_open())
    {
        cout << "打开文件失败!" << endl;
        return;
    }

    Person person;
    
    ifs.read((char*) &person, sizeof(person));

    cout << "姓名: " << person.name << endl << "年龄: " << person.age;

    ifs.close();
    
}

// int main() {
// //    write();
//    read();
//    return 0;
// }
