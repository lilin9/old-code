#ifndef MYLABEL_H
#define MYLABEL_H

#include "QLabel"

class myLabel: public QLabel
{
    Q_OBJECT
public:
    explicit myLabel(QWidget *parent = 0);

    //鼠标进入事件
    void enterEvent(QEvent *event);

    //鼠标离开事件
    void leaveEvent(QEvent *event);
};

#endif // MYLABEL_H
