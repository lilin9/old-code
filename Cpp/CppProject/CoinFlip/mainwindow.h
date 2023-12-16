#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <chooselevelscene.h>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    //重写 paintEvent 添加背景图片
    void paintEvent(QPaintEvent *);

    Chooselevelscene *chooselevelscene = NULL;

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
