//
// Created by lilin on 2023/4/25.
//

#ifndef MYPORA_MAINWINDOW_H
#define MYPORA_MAINWINDOW_H

#include <QMainWindow>


QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow {
Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = nullptr);

    ~MainWindow() override;

private:
    Ui::MainWindow *ui;
};


#endif //MYPORA_MAINWINDOW_H
