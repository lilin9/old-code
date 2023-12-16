#ifndef MYPUSHBUTTON_H
#define MYPUSHBUTTON_H

#include <QPushButton>

class MyPushButton : public QPushButton
{
    Q_OBJECT
public:
    explicit MyPushButton(QWidget *parent = nullptr);

    //参数1：正常显示的图片路径，参数2：按下后显示的图片路径
    MyPushButton(QString normalImg, QString pressImg = "");

    QString normalImgPath;
    QString pressImgPath;

    //按钮特效
    void zoomDown();    //向下跳
    void zoomTop();     //向上跳

    //重写按钮的 按下 和 释放 事件
    void mousePressEvent(QMouseEvent *event);
    void mouseReleaseEvent(QMouseEvent *event);
signals:
};

#endif // MYPUSHBUTTON_H
