#include "widget.h"
#include "ui_widget.h"
#include "QDebug"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    //点击获取按钮，获取值
    connect(ui->getValueButton, &QPushButton::clicked, [=]() {
        qDebug() << "获取值: " << ui->widget->getValue();
    });

    //点击设置按钮，设置值
    connect(ui->setValueButton, &QPushButton::clicked, [=]() {
       ui->widget->setValue(10);
    });
}

Widget::~Widget()
{
    delete ui;
}

