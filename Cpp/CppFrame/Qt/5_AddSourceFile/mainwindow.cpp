#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QIcon"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    //设置资源文件
//    QString iconPath = "D:\\Programming\\Cpp\\CppFrame\\Qt\\5_AddSourceFile\\icon.png";
//    ui->actionnew->setIcon(QIcon(iconPath));

    //添加Qt资源 （": + 前缀名 + 文件名"）
    ui->actionnew->setIcon(QIcon(":/icon.png"));
}

MainWindow::~MainWindow()
{
    delete ui;
}

