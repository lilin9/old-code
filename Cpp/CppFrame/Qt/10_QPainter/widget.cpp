#include "widget.h"
#include "ui_widget.h"
#include "QPainter"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    //点击移动图片按钮，移动图片
    connect(ui->moveButton, &QPushButton::clicked, [=]() {
        this->posX += 10;

        //如果要手动调用绘图事件，则用 update 更新
        this->update();
    });
}

//声明画板的事件
void Widget::paintEvent(QPaintEvent *event) {
//    //实例化画家对象，this 指定的是当前绘图设备
//    QPainter painter(this);

//    //设置画笔
//    QPen pen(QColor(0, 255, 0));
//    //设置画笔宽度
//    pen.setWidth(3);
//    //使用画笔
//    painter.setPen(pen);

//    //画图
//    //线
//    painter.drawLine(0,0, 100, 100);
//    //圆
//    painter.drawEllipse(QPoint(100, 100), 100, 100);
//    //矩形
//    painter.drawRect(QRect(QPoint(0, 0), QPoint(100, 100)));

    ////////////////// 利用画家绘制资源图片 ///////////////////////////
    QPainter painter(this);
    painter.drawPixmap(this->posX, 20, QPixmap(":/image/cat.jpg"));

    //如果 posX 大于屏幕宽度了，将其置 0
    if (this->posX > this->width()) {
        this->posX = 0;
    }
}

Widget::~Widget()
{
    delete ui;
}

