#ifndef WIDGET_H
#define WIDGET_H

//包含头文件 QWidget 窗口类
#include <QWidget>

class Widget : public QWidget
{
    //宏，允许类中使用信号和槽的机制
    Q_OBJECT

public:
    //构造函数
    Widget(QWidget *parent = nullptr);
    //析构
    ~Widget();
};
#endif // WIDGET_H
