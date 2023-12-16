#include "widget.h"
#include "QPushButton"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
{
    //创建一个按钮
    QPushButton* btn = new QPushButton;
    //显示按钮  以顶层复式弹出窗口控件
//    btn->show();
    //使 btn 对象，依赖在 widget 窗口中
    btn->setParent(this);
    //显示文本
    btn->setText("第一个按钮");

    //创建第二个按钮，按照控件大小创建窗口
    QPushButton* btn2 = new QPushButton("第二个按钮", this);
    //移动按钮
    btn2->move(150, 0);
    //重新指定按钮大小
    btn2->resize(100, 100);
    //重置窗口大小
    resize(600, 400);

    //修改窗口标题
    this->setWindowTitle("你好，世界");
    //设置窗口固定大小
    this->setFixedSize(600, 400);
}

Widget::~Widget()
{

}

