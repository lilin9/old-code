#include <iostream>
// 1.引入头文件
#include <fstream>

using namespace std;

// void write() {
//     // 2.创建对象
//     ifstream ofs;

//     // 3.打开文件
//     ofs.open("text.txt", ios::out);

//     // 4.写入数据
//     ofs << "i love you then thousand years" << endl;

//     // 5.关闭文件
//     ofs.close();
// }

void read() {
    // 2. 创建流对象
    ifstream ifs;

    // 3. 打开文件
    ifs.open("text.txt", ios::in);

    // 4. 判断是否打开成功
    if (!ifs.is_open()) {
        cout << "打开文件失败!" << endl;
        return;
    }

    // 5. 读取数据
    // char buffer[1024] = {0};
    // while (ifs >> buffer) {
    //     cout << buffer << endl;
    // }

    // char buffer[1024] = {0};
    // while (ifs.getline(buffer, sizeof(buffer))) {
    //     cout << buffer << endl;
    // }

    // string buffer;
    // while (getline(ifs, buffer)) {
    //     cout << buffer << endl;
    // }

    char byt;
    while ((byt = ifs.get()) != EOF)
    {
        cout << byt << endl;
    }
    

    // 6. 关闭文件
    ifs.close();
}

int main() {
    read();
    return 0;
}
