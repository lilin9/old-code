#include "chooselevelscene.h"
#include "QPainter"
#include "QMenuBar"
#include "QDebug"
#include "QTimer"
#include "QLabel"
#include "QSound"
#include "mypushbutton.h"

Chooselevelscene::Chooselevelscene(QWidget *parent) : QMainWindow(parent)
{
    //设置主场景
    //固定屏幕宽高
    this->setFixedSize(350, 588);
    //设置图标
    this->setWindowIcon(QIcon(":/resource/Coin0001.png"));
    //标题设置
    this->setWindowTitle("选择关卡");

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

    //添加选择按钮音效
    QSound *chooseSound = new QSound(":/resource/TapButtonSound.wav", this);
    //添加返回按钮音效
    QSound *backSound = new QSound(":/resource/BackButtonSound.wav", this);

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

    //创建关卡选择按钮
    for (int i = 0; i < 20; i++) {
        MyPushButton *menuButton = new MyPushButton(":/resource/LevelIcon.png");
        menuButton->setParent(this);
        //设置每个按钮的位置
        menuButton->move(40 + i%4*70, 130 + i/4*70);
        //监听每个按钮的点击事件
        connect(menuButton, &MyPushButton::clicked, [=]() {
            //播放选择音效
            chooseSound->play();

           //隐藏 选择关卡 场景
            this->hide();
            //创建游戏场景
            play = new PlayScence(i+1);
            //设置 游戏场景 的初始位置
            play->setGeometry(this->geometry());
            //显示游戏场景
            play->show();

            //监听 游戏关卡 页面的 返回按钮 是否有被点击
            connect(play, &PlayScence::clickBackButton, [=]() {
                this->setGeometry(play->geometry());

                //播放返回音效
                backSound->play();

                //将 游戏关卡页面 删除
                delete  play;
                play = NULL;
                //将 选择场景页面 显示
                this->show();
            });
        });

        //设置每个关卡按钮的数字
        QLabel *label = new QLabel;
        label->setParent(this);
        //设置 label 的大小
        label->setFixedSize(menuButton->width(), menuButton->height());
        //设置文字
        label->setText(QString::number(i+1));
        //设置 label 位置
        label->move(40 + i%4*70, 130 + i/4*70);
        //设置 label 中文字垂直水平居中
        label->setAlignment(Qt::AlignHCenter | Qt::AlignVCenter);
        //给 label 设置鼠标穿透，让鼠标的点击操作能够作用到 button 上
        label->setAttribute(Qt::WA_TransparentForMouseEvents);
    }
}


//重写绘图事件
void Chooselevelscene::paintEvent(QPaintEvent *) {
    QPainter painter(this);
    QPixmap pix;
    //加载背景图片
    pix.load(":/resource/OtherSceneBg.png");
    painter.drawPixmap(0, 0, this->width(), this->height(), pix);

    //添加标题
    pix.load(":/resource/Title.png");
    painter.drawPixmap((this->width() - pix.width())*0.5, 30, pix.width(), pix.height(), pix);
}

Chooselevelscene::~Chooselevelscene() {
}
