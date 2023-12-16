#include "playscence.h"
#include "QDebug"
#include "QMenuBar"
#include "QPainter"
#include "mypushbutton.h"
#include "QTimer"
#include "QLabel"
#include "coin.h"
#include "QSound"
#include "dataconfig.h"
#include "QPropertyAnimation"

PlayScence::PlayScence(QWidget *parent) : QMainWindow(parent)
{

}

PlayScence::PlayScence(int levelNum) {
    this->levelIndex = levelNum;
    //设置游戏场景
    //固定屏幕宽高
    this->setFixedSize(350, 588);
    //设置图标
    this->setWindowIcon(QIcon(":/resource/Coin0001.png"));
    //标题设置
    this->setWindowTitle("翻金币");

    //创建菜单栏
    QMenuBar *bar = menuBar();
    this->setMenuBar(bar);
    //添加 开始 菜单项
    QMenu *startMenu = bar->addMenu("开始");
    //在 开始 菜单里创建 退出 选项
    QAction *exitAction = startMenu->addAction("退出");

    //当 点击退出选项 时，退出程序
    connect(exitAction, &QAction::triggered, [=]() {
        this->close();
    });

    //添加翻金币音效
    QSound *flipSound = new QSound(":/resource/ConFlipSound.wav", this);
    //添加胜利音效
    QSound *winSound = new QSound(":/resource/LevelWinSound.wav", this);

    //添加返回按钮
    MyPushButton *backButton = new MyPushButton(":/resource/BackButton.png", ":/resource/BackButtonSelected.png");
    backButton->setParent(this);
    backButton->move(this->width()-backButton->width(), this->height()-backButton->height());

    //当用户点击 返回按钮 时
    connect(backButton, &MyPushButton::clicked, [=]() {
        //延时 0.5 s
        QTimer::singleShot(500, [=]() {
            //连接按钮的自定义信号，提示主场景 返回按钮 被点击
            emit this->clickBackButton();
        });
    });

    //显示当前的 关卡数
    QLabel *label = new QLabel();
    label->setParent(this);
    //设置 Label 位置
    label->setGeometry(30, this->height() - 50, 130, 50);
    //设置字体
    QFont font;
    font.setFamily("华文新魏");
    font.setPointSize(16);
    label->setFont(font);
    //设置文本
    label->setText(QString("Level %1").arg(levelNum));

    //将每关的关卡情况从配置文件中取出
    dataConfig config;
    for(int i=0; i < 4; i++) {
        for (int j=0; j < 4; j++) {
            this->gameArray[i][j] = config.mData[this->levelIndex][i][j];
        }
    }

    //添加胜利图片（此时隐藏）
    QLabel *winLabel = new QLabel;
    QPixmap winPix;
    winPix.load(":/resource/LevelCompletedDialogBg.png");
    winLabel->setGeometry(0, 0, winPix.width(), winPix.height());
    winLabel->setPixmap(winPix);
    winLabel->setParent(this);
    winLabel->move((this->width() - winPix.width())*0.5, -winPix.height());

    //显示金币背景图案
    for(int i=0; i < 4; i++) {
        for (int j=0; j < 4; j++) {
            //绘制背景图片
            QPixmap pix = QPixmap(":/resource/BoardNode.png");
            QLabel *label = new QLabel;
            label->setGeometry(0, 0, pix.width(), pix.height());
            label->setPixmap(pix);
            label->setParent(this);
            label->move(67 + i*55, 200 + j*55);

            //创建金币/银币
            QString path;
            if (this->gameArray[i][j] == 1) {
                path = ":/resource/Coin0001.png";
            } else {
                path = ":/resource/Coin0008.png";
            }
            Coin *coin = new Coin(path);
            coin->setParent(this);
            coin->move(69 + i*55, 202 + j*55);

            //初始化属性
            coin->posX = i;
            coin->posY = j;
            coin->flag = gameArray[i][j];   //flag=1 为正面，flag=0 为反面

            //将用户点击的金币记录下来
            this->clickedCoin[i][j] = coin;

            //点击硬币，翻转
            connect(coin, &Coin::clicked, [=](){
                //播放翻金币音效
                flipSound->play();

                coin->changeFlag();

                //在翻转按钮时，禁用其他按钮的点击
                for(int i=0; i < 4; i++) {
                    for (int j=0; j < 4; j++) {
                        this->clickedCoin[i][j]->isWin = true;
                    }
                }

                //更新数据数组
                this->gameArray[i][j] = this->gameArray[i][j] == 0 ? 1 : 0;

                //定时器，延时 0.3s
                QTimer::singleShot(300, [=](){
                    //翻转周围金币
                    //判断要翻转的金币是否存在
                    if (coin->posX+1 <= 3) { //右侧
                        this->clickedCoin[coin->posX+1][coin->posY]->changeFlag();
                        //更新数据数组
                        this->gameArray[coin->posX+1][coin->posY] = this->gameArray[coin->posX+1][coin->posY] == 0 ? 1 : 0;
                    }
                    if (coin->posX-1 >= 0) { //左侧
                        this->clickedCoin[coin->posX-1][coin->posY]->changeFlag();
                        //更新数据数组
                        this->gameArray[coin->posX-1][coin->posY] = this->gameArray[coin->posX-1][coin->posY] == 0 ? 1 : 0;
                    }
                    if (coin->posY+1 <= 3) { //上侧
                        this->clickedCoin[coin->posX][coin->posY+1]->changeFlag();
                        //更新数据数组
                        this->gameArray[coin->posX][coin->posY+1] = this->gameArray[coin->posX][coin->posY+1] == 0 ? 1 : 0;
                    }
                    if (coin->posY-1 >= 0) { //下侧
                        this->clickedCoin[coin->posX][coin->posY-1]->changeFlag();
                        //更新数据数组
                        this->gameArray[coin->posX][coin->posY-1] = this->gameArray[coin->posX][coin->posY-1] == 0 ? 1 : 0;
                    }

                    //在翻转完按钮时，还原按钮
                    for(int i=0; i < 4; i++) {
                        for (int j=0; j < 4; j++) {
                            this->clickedCoin[i][j]->isWin = false;
                        }
                    }

                    //判断是否胜利
                    this->isWin = true;
                    for(int i=0; i < 4; i++) {
                        for (int j=0; j < 4; j++) {
                            //只要有一个是反面，就算失败
                            if (clickedCoin[i][j]->flag == false) {
                                this->isWin = false;
                                break;
                            }
                        }
                    }
                    if (this->isWin == true) {
                        //播放胜利音效
                        winSound->play();

                        for(int i=0; i < 4; i++) {
                            for (int j=0; j < 4; j++) {
                                clickedCoin[i][j]->isWin = true;
                            }
                        }

                        //显示胜利图片
                        QPropertyAnimation *animation = new QPropertyAnimation(winLabel, "geometry");
                        //设置时间间隔  1s
                        animation->setDuration(1000);
                        //设置开始位置
                        animation->setStartValue(QRect(winLabel->x(), winLabel->y(), winLabel->width(), winLabel->height()));
                        //设置结束位置
                        animation->setEndValue(QRect(winLabel->x(), winLabel->y()+114, winLabel->width(), winLabel->height()));
                        //设置缓和曲线
                        animation->setEasingCurve(QEasingCurve::OutBounce);
                        //执行动画
                        animation->start();
                    }
                });
            });
        }
    }
}

//重写绘图事件
void PlayScence::paintEvent(QPaintEvent *) {
    QPainter painter(this);
    QPixmap pix;
    //加载背景图片
    pix.load(":/resource/PlayLevelSceneBg.png");
    painter.drawPixmap(0, 0, this->width(), this->height(), pix);

    //添加标题
    pix.load(":/resource/Title.png");
    pix = pix.scaled(pix.width() * 0.5, pix.height()*0.5);
    painter.drawPixmap(10, 30, pix.width(), pix.height(), pix);
}
