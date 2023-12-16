#include "widget.h"

#include <QApplication> //包含一个应用程序类的头文件

int main(int argc, char *argv[])
{
    //创建应用程序对象。在Qt中应用程序对象，有且仅有一个
    QApplication a(argc, argv);
    //窗口对象
    Widget w;
    //显示窗口对象
    w.show();
    //让应用程序对象进入消息循环机制
    return a.exec();
}
