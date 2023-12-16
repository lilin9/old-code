#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QPainter"
#include "mypushbutton.h"
#include "QTimer"
#include "QSound"
#include "QDebug"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    //设置主场景
    //固定屏幕宽高
    this->setFixedSize(350, 588);
    //设置图标
    this->setWindowIcon(QIcon(":/resource/Coin0001.png"));
    //标题设置
    this->setWindowTitle("首页");

    //点击退出按钮，退出程序
    connect(ui->actionExit, &QAction::triggered, [=](){this->close();});

    //添加 开始按钮 的音效
    QSound *startSound = new QSound(":/resource/TapButtonSound.wav", this);
    //添加返回按钮音效
    QSound *backSound = new QSound(":/resource/BackButtonSound.wav", this);

    //添加开始按钮
    MyPushButton *startButton = new MyPushButton(":/resource/MenuSceneStartButton.png");
    startButton->setParent(this);
    startButton->move(this->width()*0.5 - startButton->width()*0.5, this->height()*0.7);

    this->chooselevelscene = new Chooselevelscene(this);

    //监听 选择关卡 页面的 返回按钮 是否有被点击
    connect(chooselevelscene, &Chooselevelscene::clickBackButton, [=]() {
        this->setGeometry(chooselevelscene->geometry());
        //播放返回音效
        backSound->play();
        //将 选择关卡页面 隐藏
        chooselevelscene->hide();
        //将 进入场景页面 显示
        this->show();
    });

    //当点击开始按钮时
    connect(startButton, &MyPushButton::clicked, [=](){
        //播放音效资源
        startSound->play();
        qDebug() << "音效被加载";

        //添加 开始按钮 的点击动画
        startButton->zoomDown();
        startButton->zoomTop();

        //先睡 0.5s 再进入到选择关卡场景中
        QTimer::singleShot(500, [=]() {
            //设置 chooselevelscene.cpp 场景的位置
            chooselevelscene->setGeometry(this->geometry());
            this->hide();   //将 进入场景 隐藏
            this->chooselevelscene->show(); //将 选择关卡场景 显示出来
        });
    });
}

//重写 paintEvent 添加背景图片
void MainWindow::paintEvent(QPaintEvent *) {
    QPainter painter(this);
    QPixmap pix;
    //加载背景图片
    pix.load(":/resource/PlayLevelSceneBg.png");
    painter.drawPixmap(0, 0, this->width(), this->height(), pix);

    //添加标题
    pix.load(":/resource/Title.png");
    //图片太大，进行一定比例缩放
    pix = pix.scaled(pix.width() * 0.5, pix.height() * 0.5);
    painter.drawPixmap(10, 30, pix);
}

MainWindow::~MainWindow()
{
    delete ui;
}

