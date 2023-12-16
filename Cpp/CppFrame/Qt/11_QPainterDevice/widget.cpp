#include "widget.h"
#include "ui_widget.h"
#include "QPixmap"
#include "QPainter"
#include "QImage"
#include "QPicture"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

//    //Pixmap 绘图设备（专门为平台显示做了优化）
//    QPixmap pixmap(600, 400);

//    //声明画家
//    QPainter painter(&pixmap);
//    //填充背景颜色
//    pixmap.fill(Qt::red);
//    //画个圆
//    painter.setPen(Qt::green);
//    painter.drawEllipse(QPoint(300, 200), 100, 100);

//    //往磁盘写入画作
//    pixmap.save("D:/Programming/Cpp/CppFrame/Qt/11_QPainterDevice/pix.jpg");


//    //QImage 绘图设备（可以对像素进行访问）
//    QImage img(600, 400, QImage::Format_RGB32);

//    //声明画家
//    QPainter painter2(&img);
//    //填充背景颜色
//    img.fill(Qt::white);
//    //画个圆
//    painter2.setPen(Qt::blue);
//    painter2.drawEllipse(QPoint(300, 200), 100, 100);

//    //往磁盘写入画作
//    img.save("D:/Programming/Cpp/CppFrame/Qt/11_QPainterDevice/img.jpg");


    //QPicture 绘图设备（可以记录和重新绘图指令）
    QPicture pic;
    QPainter painter;
    //开始在 pic 上作画
    painter.begin(&pic);
    painter.setPen(QPen(Qt::cyan));
    painter.drawEllipse(QPoint(300, 200), 100, 100);
    //结束画画
    painter.end();

    pic.save("D:/Programming/Cpp/CppFrame/Qt/11_QPainterDevice/pic.jpg");
}

Widget::~Widget()
{
    delete ui;
}

