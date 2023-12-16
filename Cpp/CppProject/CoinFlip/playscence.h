#ifndef PLAYSCENCE_H
#define PLAYSCENCE_H

#include <QMainWindow>
#include <coin.h>

class PlayScence : public QMainWindow
{
    Q_OBJECT
public:
    explicit PlayScence(QWidget *parent = nullptr);
    PlayScence(int levelNum);

    //记录游戏的场次
    int levelIndex;
    //是否胜利的标志
    bool isWin;

    //用来保存每关要展示的关卡情况
    int gameArray[4][4];
    //维护用来记录用户点击哪个金币的数组
    Coin *clickedCoin[4][4];

    //重写绘图事件
    void paintEvent(QPaintEvent *);
signals:
    //自定义信号，提示主场景 返回按钮 被点击
    void clickBackButton();
};

#endif // PLAYSCENCE_H
