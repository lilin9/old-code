#include "mypushbutton.h"
#include "QDebug"
#include "QPropertyAnimation"

MyPushButton::MyPushButton(QWidget *parent) : QPushButton(parent)
{

}

//参数1：正常显示的图片路径，参数2：按下后显示的图片路径
MyPushButton::MyPushButton(QString normalImg, QString pressImg) {
    this->normalImgPath = normalImg;
    this->pressImgPath = pressImg;

    //加载按钮的图片
    QPixmap pix;
    bool isLoad = pix.load(normalImg);
    //判断图片是否加载成功
    if (!isLoad) {
        qDebug() << "图片加载失败";
        return;
    }

    //固定自定义按钮大小
    this->setFixedSize(pix.width(), pix.height());
    //设置自定义按钮样式
    this->setStyleSheet("QPushButton{border:0px;}");
    //设置自定义按钮图标
    this->setIcon(pix);
    //设置自定义按钮的图标大小
    this->setIconSize(QSize(pix.width(), pix.height()));
}


//按钮特效
void MyPushButton::zoomDown() {    //向下跳
    //创建动画对象
    QPropertyAnimation *animation = new QPropertyAnimation(this, "geometry");
    //设置动画时间间隔
    animation->setDuration(500);    //500 ms

    //设置起始位置
    animation->setStartValue(QRect(this->x(), this->y(), this->width(), this->height()));
    //设置结束位置
    animation->setEndValue(QRect(this->x(), this->y()+10, this->width(), this->height()));

    //设置弹跳曲线
    animation->setEasingCurve(QEasingCurve::OutBounce);

    //执行动画
    animation->start();
}

void MyPushButton::zoomTop(){     //向上跳
    //创建动画对象
    QPropertyAnimation *animation = new QPropertyAnimation(this, "geometry");
    //设置动画时间间隔
    animation->setDuration(500);    //500 ms

    //设置起始位置
    animation->setStartValue(QRect(this->x(), this->y()+10, this->width(), this->height()));
    //设置结束位置
    animation->setEndValue(QRect(this->x(), this->y(), this->width(), this->height()));

    //设置弹跳曲线
    animation->setEasingCurve(QEasingCurve::OutBounce);

    //执行动画
    animation->start();

}

//重写按钮的 按下 和 释放 事件
void MyPushButton::mousePressEvent(QMouseEvent *event) {  //按下
    //传入的 按下图片 不为空，说明需要有 按下状态，需要切换图片
    if (this->pressImgPath != "") {
        //加载按钮的图片
        QPixmap pix;
        bool isLoad = pix.load(this->pressImgPath);
        //判断图片是否加载成功
        if (!isLoad) {
            qDebug() << "图片加载失败";
            return;
        }

        //固定自定义按钮大小
        this->setFixedSize(pix.width(), pix.height());
        //设置自定义按钮样式
        this->setStyleSheet("QPushButton{border:0px;}");
        //设置自定义按钮图标
        this->setIcon(pix);
        //设置自定义按钮的图标大小
        this->setIconSize(QSize(pix.width(), pix.height()));
    }
    return QPushButton::mousePressEvent(event);
}

void MyPushButton::mouseReleaseEvent(QMouseEvent *event){ //释放
    //传入的 按下图片 不为空，说明需要有 释放状态，需要切换图片
    if (this->pressImgPath != "") {
        //加载按钮的图片
        QPixmap pix;
        bool isLoad = pix.load(this->normalImgPath);
        //判断图片是否加载成功
        if (!isLoad) {
            qDebug() << "图片加载失败";
            return;
        }

        //固定自定义按钮大小
        this->setFixedSize(pix.width(), pix.height());
        //设置自定义按钮样式
        this->setStyleSheet("QPushButton{border:0px;}");
        //设置自定义按钮图标
        this->setIcon(pix);
        //设置自定义按钮的图标大小
        this->setIconSize(QSize(pix.width(), pix.height()));
    }
    return QPushButton::mouseReleaseEvent(event);
}
