#ifndef WIDGET_H
#define WIDGET_H

#include <QWidget>

QT_BEGIN_NAMESPACE
namespace Ui { class Widget; }
QT_END_NAMESPACE

class Widget : public QWidget
{
    Q_OBJECT

public:
    //定时器唯一标识
    int id1;
    int id2;

    Widget(QWidget *parent = nullptr);
    ~Widget();

    //重写定时器事件
    void timerEvent(QTimerEvent *);

private:
    Ui::Widget *ui;
};
#endif // WIDGET_H
