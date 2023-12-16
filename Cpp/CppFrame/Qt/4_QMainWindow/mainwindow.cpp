#include "mainwindow.h"
#include "QMenuBar"
#include "QToolBar"
#include "QStatusBar"
#include "QLabel"
#include "QDockWidget"
#include "QTextEdit"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
{
    //重置窗口大小
    this->resize(600, 400);

    //创建菜单栏（菜单栏至多只能有一个）
    QMenuBar *bar = menuBar();
    this->setMenuBar(bar);

    //添加菜单内容
    QMenu *fileMenu = bar->addMenu("文件");
    QMenu *editMenu = bar->addMenu("编辑");

    //添加菜单项
    QAction *newAction = fileMenu->addAction("新建");
    //添加分割线
    fileMenu->addSeparator();
    QAction *openAction = fileMenu->addAction("打开");


    //创建工具栏（工具栏可以有多个）
    QToolBar *toolBar = new QToolBar(this);
    this->addToolBar(Qt::LeftToolBarArea, toolBar);

    //设置默认停靠范围，只允许左右停靠
    toolBar->setAllowedAreas(Qt::LeftToolBarArea | Qt::RightToolBarArea);

    //设置不允许浮动
    toolBar->setFloatable(false);

    //设置工具栏内容
    toolBar->addAction(newAction);
    //添加分割线
    fileMenu->addSeparator();
    toolBar->addAction(openAction);



    //创建状态栏（状态栏只能有一个）
    QStatusBar *status = statusBar();
    this->setStatusBar(status);
    //添加标签控件
    QLabel *label = new QLabel("提示信息", this);
    status->addWidget(label);

    QLabel *label2 = new QLabel("右侧提示信息", this);
    status->addPermanentWidget(label2);


    //浮动窗口（可以有多个）
    QDockWidget *widget = new QDockWidget("浮动", this);
    this->addDockWidget(Qt::BottomDockWidgetArea, widget);
    //设置浮动窗口只允许上下停靠
    widget->setAllowedAreas(Qt::TopDockWidgetArea | Qt::BottomDockWidgetArea);


    //设置中心部件（只能有一个）
    QTextEdit *edit = new QTextEdit(this);
    this->setCentralWidget(edit);
}

MainWindow::~MainWindow()
{
}

