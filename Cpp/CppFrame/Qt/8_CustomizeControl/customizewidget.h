#ifndef CUSTOMIZEWIDGET_H
#define CUSTOMIZEWIDGET_H

#include <QWidget>

namespace Ui {
class CustomizeWidget;
}

class CustomizeWidget : public QWidget
{
    Q_OBJECT

public:
    explicit CustomizeWidget(QWidget *parent = nullptr);
    ~CustomizeWidget();

    //设置值
    void setValue(int value);

    //获取值
    int getValue();

private:
    Ui::CustomizeWidget *ui;
};

#endif // CUSTOMIZEWIDGET_H
