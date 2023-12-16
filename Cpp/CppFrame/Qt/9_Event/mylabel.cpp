#include "mylabel.h"
#include "QDebug"

myLabel::myLabel(QWidget *parent): QLabel(parent)
{

}

//鼠标进入事件
void myLabel::enterEvent(QEvent *event) {
    qDebug() << "鼠标进入事件触发";
}

//鼠标离开事件
void myLabel::leaveEvent(QEvent *event) {
    qDebug() << "鼠标离开事件触发";
}
