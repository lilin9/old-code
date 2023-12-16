#include "customizewidget.h"
#include "ui_customizeWidget.h"

CustomizeWidget::CustomizeWidget(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::CustomizeWidget)
{
    ui->setupUi(this);

    //QSpinBox移动，QSlider 跟着滑动
    void(QSpinBox:: *spSignal)(int) = &QSpinBox::valueChanged;
    connect(ui->spinBox, spSignal, ui->horizontalSlider, &QSlider::setValue);

    //QSlider 滑动，QSpinBox 跟着移动
    connect(ui->horizontalSlider, &QSlider::valueChanged, ui->spinBox, &QSpinBox::setValue);
}

//设置值
void CustomizeWidget::setValue(int value) {
    ui->spinBox->setValue(value);
};

//获取值
int CustomizeWidget::getValue() {
    return ui->spinBox->value();
};

CustomizeWidget::~CustomizeWidget()
{
    delete ui;
}
