#ifndef STUDENT_H
#define STUDENT_H

#include "QObject"

class Student: public QObject {
    Q_OBJECT
public:
    explicit Student(QObject *parent = 0);

signals:

public slots:
    //早期 Qt 版本，必须要写到 public slots 下，高版本可以写到 public 或者全局作用域下
    //返回值 void，需要声明，也需要实现
    //可以有参数，可以发生重载
    void unhappy();

    void unhappy(QString speak);
};

#endif // STUDENT_H
