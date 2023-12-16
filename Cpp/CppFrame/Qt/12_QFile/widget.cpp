#include "widget.h"
#include "ui_widget.h"
#include "QFileDialog"
#include "QFile"
#include "QFileInfo"
#include "QDebug"
#include "QDateTime"

Widget::Widget(QWidget *parent)
    : QWidget(parent)
    , ui(new Ui::Widget)
{
    ui->setupUi(this);

    //点击按钮，打开 选择文件框
    connect(ui->openFileButton, &QPushButton::clicked, [=](){
        QString filePath = QFileDialog::getOpenFileName(this, "选择文件", "D:\\Programming\\Cpp\\CppFrame\\Qt\\12_QFile");
        //将 文件路径 放入 lineEdit 中
        ui->lineEdit->setText(filePath);

        //读取内容，放入 textEdit
        //QFile 默认支持的格式是 UTF-8
        QFile file(filePath);   //参数是要读取的文件路径
        //设置打开方式
        file.open(QIODevice::ReadOnly);

        //读取数据
//        QByteArray array = file.readAll();
        //按行读
        QByteArray array;
        while(!file.atEnd()) {
            array += file.readLine();
        }

        //将读取的数据放入 textEdit
        ui->textEdit->setText(array);

        file.close();


        //写文件
//        file.open(QIODevice::Append);
//        file.write("\n\n君子以自强不息");
//        file.close();

        //文件信息类
        QFileInfo info(filePath);
        qDebug() << "文件的大小：" << info.size() << "\n后缀名: " << info.suffix() << "\n文件名: " << info.fileName();
        qDebug() << "创建时间: " << info.created().toString("yyyy-MM-dd hh:mm:ss") << "\n修改时间：" << info.lastModified().toString("yyyy-MM-dd hh:mm:ss");
    });
}

Widget::~Widget()
{
    delete ui;
}

