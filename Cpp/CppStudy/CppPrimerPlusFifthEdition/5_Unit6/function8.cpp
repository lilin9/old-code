#include "iostream"
#include "fstream"

using namespace std;

/*
 * 编写一个程序，它打开一个文本文件，逐个字符的读取该文件，直到到达文件末尾，然后指出
 * 该文件中包含多少个字符。
 */
int main8() {
    //读文件对象
    ifstream fin;

    //打开文件
    fin.open(R"(D:\Programming\Cpp\CppStudy\CppPrimerPlusFifthEdition\5_Unit6\text.txt)",
             ios::in);

    //判断文件是不是打开了
    if(!fin) {
        std::cerr<<"cannot open the file";
        return 0;
    }

    //读文件
    char item;
    //字符个数
    int count = 0;
    while ((item = fin.get()) != EOF) {
        if (item != ' ') {
            count++;
        }
    }
    cout << "this file has " << count << " characters" << endl;
    return 0;
}