#include "widget.h"
#include "QPushButton"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
{
    //创建按钮对象
    QPushButton* btn = new QPushButton;
    //将按钮添加到窗口中
    btn->setParent(this);
    //添加按钮提示文字
    btn->setText("点击按钮");
    //修改窗口起始大小
    this->resize(600, 400);
    //修改按钮的位置
    btn->move(200, 150);

    //点击按钮关闭窗口
    //参数1：信号的发送者   参数2：发送的信号（函数的地址）   参数3：信号的接收者   参数4：处理的槽函数
    connect(btn, &QPushButton::clicked, this, &Widget::close);
}

Widget::~Widget()
{
}
















