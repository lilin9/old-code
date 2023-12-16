#ifndef CHOOSELEVELSCENE_H
#define CHOOSELEVELSCENE_H

#include <QMainWindow>
#include <playscence.h>

class Chooselevelscene : public QMainWindow
{
    Q_OBJECT
public:
    explicit Chooselevelscene(QWidget *parent = nullptr);
    ~Chooselevelscene();

    //重写绘图事件
    void paintEvent(QPaintEvent *);

    //游戏场景对象指针
    PlayScence *play = NULL;
signals:
    //自定义信号，提示主场景 返回按钮 被点击
    void clickBackButton();
};

#endif // CHOOSELEVELSCENE_H
