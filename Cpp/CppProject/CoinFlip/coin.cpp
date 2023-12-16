#include "coin.h"
#include "QDebug"
#include "QPushButton"

Coin::Coin(QWidget *parent) : QPushButton(parent)
{

}

//参数：表示传入的是金币路径还是银币路径
Coin::Coin(QString btnPath) {
    QPixmap pix;
    bool result = pix.load(btnPath);

    //判断图片是否加载成功
    if (!result) {
        qDebug() << QString("图片 %1 加载失败").arg(btnPath);
        return;
    }

    //设置样式
    this->setFixedSize(pix.width(), pix.height());
    this->setStyleSheet("QPushButton{border:0px;}");
    this->setIcon(pix);
    this->setIconSize(QSize(pix.width(), pix.height()));

    //初始化定时器
    this->timer1 = new QTimer(this);
    this->timer2 = new QTimer(this);
    //监听正面翻反面的信号，并且翻转金币
    connect(this->timer1, &QTimer::timeout, [=](){
        QPixmap pix;
        QString path = QString(":/resource/Coin000%1.png").arg(this->min++);
        pix.load(path);

        //设置样式
        this->setFixedSize(pix.width(), pix.height());
        this->setStyleSheet("QPushButton{border:0px;}");
        this->setIcon(pix);
        this->setIconSize(QSize(pix.width(), pix.height()));

        //结束定时器
        if (this->min > this->max) {
            this->min = 1;
            this->isAnimation = false;
            timer1->stop();
        }
    });
    //监听反面翻正面的信号，并且翻转金币
    connect(this->timer2, &QTimer::timeout, [=](){
        QPixmap pix;
        QString path = QString(":/resource/Coin000%1.png").arg(this->max--);
        pix.load(path);

        //设置样式
        this->setFixedSize(pix.width(), pix.height());
        this->setStyleSheet("QPushButton{border:0px;}");
        this->setIcon(pix);
        this->setIconSize(QSize(pix.width(), pix.height()));

        //结束定时器
        if (this->max < this->min) {
            this->max = 8;
            this->isAnimation = false;
            timer2->stop();
        }
    });
}

//改变标志的方法
void Coin::changeFlag() {
    if (this->flag) { //金币翻银币
        this->timer1->start(30);
        this->flag = false;
    } else {    //银币翻金币
        this->timer2->start(30);
        this->flag = true;
    }
    this->isAnimation = true;
}


//重写鼠标点击事件
void Coin::mousePressEvent(QMouseEvent *e) {
    //如果金币正在执行动画，就把点击事件拦截
    if (this->isAnimation || this->isWin) {
        return;
    } else {
        //没有直接放行即可
        QPushButton::mousePressEvent(e);
    }
}
