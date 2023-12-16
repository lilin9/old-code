#include "widget.h"
#include "ui_widget.h"
#include "QTimer"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    //调用计时器
    //参数1：时间间隔 单位：毫秒
    id1 = startTimer(1000);
    id2 = startTimer(2000);


    //定时器的第二种方式
    QTimer *timer = new QTimer(this);
    //启动定时器
    timer->start(500);

    //连接控件
    connect(timer, &QTimer::timeout, [=]() {
        static int num2 = 1;
        ui->label_4->setText(QString::number(num2++));
    });


    //点击暂停按钮，暂停计时器
    connect(ui->stopButton, &QPushButton::clicked, [=]() {
        timer->stop();
    });
}

//重写定时器事件
void Widget::timerEvent(QTimerEvent *event) {
    static int num = 1;
    static int num1 = 1;

    //如果是第一个定时器
    if (event->timerId() == this->id1) {
        ui->label_2->setText(QString::number(num++));
    }
    //如果是第二个定时器
    if (event->timerId() == this->id2) {
        ui->label_3->setText(QString::number(num1++));
    }
}

Widget::~Widget()
{
    delete ui;
}

