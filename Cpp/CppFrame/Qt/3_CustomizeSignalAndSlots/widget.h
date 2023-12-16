#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>
#include <Student.h>
#include <Teacher.h>

class Widget : public QWidget
{
    Q_OBJECT

public:
    Student* student;
    Teacher* teacher;

    Widget(QWidget *parent = nullptr);
    ~Widget();

    //自定义下课函数
    void finishClass();
};
#endif // WIDGET_H
