#ifndef COIN_H
#define COIN_H

#include <QMainWindow>
#include <QPushButton>
#include <QTimer>

class Coin : public QPushButton
{
    Q_OBJECT
public:
    explicit Coin(QWidget *parent = nullptr);

    //参数：表示传入的是金币路径还是银币路径
    Coin(QString btnPath);

    //金币 x 坐标
    int posX;
    //金币 y 坐标
    int posY;
    //金币是否翻转
    bool flag;
    //金币是否在执行动画
    bool isAnimation = false;
    //是否胜利的标志
    bool isWin = false;
    //金币翻银币定时器
    QTimer *timer1;
    //银币翻金币定时器
    QTimer *timer2;
    int min = 1;
    int max = 8;

    //改变标志的方法
    void changeFlag();
    //重写鼠标点击事件
    void mousePressEvent(QMouseEvent *e);
signals:

};

#endif // COIN_H
