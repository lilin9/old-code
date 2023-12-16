#include "iostream"

using namespace std;

/*
 * 编写一个菜单驱动程序的雏形。该程序显示一个提供4个选项的菜单——每个选项用一个字母标记。如果用户
 * 使用有效选项之外的字母进行响应，程序将提示用户输入一个有效的字母，用户这样做为止。然后，该程序
 * 使用一条 switch 语句，根据用户的选择执行一个简单操作。该程序的运行情况如下：
 * Please enter one of the following choices:
 * c) carnivore     p) pianist
 * t) tree          g) game
 * f
 * Please enter a c, p, t, or g: q
 * Please enter a c, p, t, or g: t
 * A maple is a tree.
 */
int main3() {
    cout << "Please enter one of the following choices:" << endl;
    cout << "c) carnivore     p) pianist" << endl;
    cout << "t) tree          g) game" << endl;

    char input;
    cin >> input;

    while (true) {
        switch (input) {
            case 'c':
                cout << "lion tiger and cheetah" << endl;
                return 0;
            case 'p':
                cout << "I will be a pianist" << endl;
                return 0;
            case 't':
                cout << "A maple is a tree." << endl;
                return 0;
            case 'g':
                cout << "Come play game with me" << endl;
                return 0;
            default:
                cout << "Please enter a c, p, t, or g: ";
                cin >> input;
        }

    }
}