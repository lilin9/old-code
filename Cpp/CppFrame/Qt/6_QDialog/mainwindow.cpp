#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "QDialog"
#include "QDebug"
#include "QMessageBox"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    //点击新建，弹出对话框
    connect(ui->actionNew, &QAction::triggered, [=]() {
        //对话框分为
        //模态对话框（不可以对其他窗口操作）     非模态对话框（可以对其他窗口进行操作）

        //模态对话框
//        QDialog dialog(this);
//        //设置对话框大小
//        dialog.resize(300, 200);
//        //对话框阻塞
//        dialog.exec();

//        qDebug("对话框弹出");

        //非模态对话框
//        QDialog *dialog = new QDialog(this);
//        dialog->resize(300, 200);
//        //当对话框被关闭时，将创建在堆区的对话框删除
//        dialog->setAttribute(Qt::WA_DeleteOnClose);
//        dialog->show();

        //消息对话框
        //错误对话框
//        QMessageBox::critical(this, "对话框", "error");

        //消息对话框
//        QMessageBox::information(this, "消息对话框", "info");

        //提问对话框
        //参数1：父亲，参数2：标题，参数3：按键类型，参数4：默认关联回车按键
        QMessageBox::question(this, "提问对话框", "question", QMessageBox::Save | QMessageBox::Cancel, QMessageBox::Cancel);
    });
}

MainWindow::~MainWindow()
{
    delete ui;
}

