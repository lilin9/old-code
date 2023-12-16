#include "iostream"
#include "string.h"

using namespace std;

/*
 * 下面是一个结构体声明：
 * struct box {
 *      char maker[40];
 *      float height;
 *      float width;
 *      float length;
 *      float volume;
 * }
 * a. 编写一个函数，按值传递 box 结构，并显示每个成员的值。
 * b. 编写一个函数，传递 box 结构的地址，并将 volume 成员设置为其他三维长度的乘积。
 * c. 编写一个使用这两个函数的简单程序。
 */

struct Box {
    char maker[40];
    float height;
    float width;
    float length;
    float volume;
};

void b(Box &box) {
    cout << "\nb: " << endl;
    cout << "maker: ";
    for (auto maker: box.maker) {
        cout << maker << " ";
    }
    cout << "\nheight: " << box.height << endl;
    cout << "width: " << box.width << endl;
    cout << "length: " << box.length << endl;
    box.volume = box.height * box.width * box.length;
    cout << "volume: " << box.volume << endl;
}

void a(Box box) {
    cout << "a: " << endl;
    cout << "maker: ";
    for (auto maker: box.maker) {
        cout << maker << " ";
    }
    cout << "\nheight: " << box.height << endl;
    cout << "width: " << box.width << endl;
    cout << "length: " << box.length << endl;
    cout << "volume: " << box.volume << endl;
}

int main3() {
    char maker[40];
    for (int i = 0; i < 40; ++i) {
        maker[i] = (char) (65 + i);
    }

    Box box;
    strcpy(box.maker, maker);
    box.height = 20;
    box.width = 7;
    box.length = 15;
    box.volume = 0;

    a(box);
    b(box);
    return 0;
}