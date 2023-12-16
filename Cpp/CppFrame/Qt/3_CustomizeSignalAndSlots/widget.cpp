#include "widget.h"
#include "QDebug"
#include "QPushButton"

/**
 * @brief Widget::Widget
 * @param parent
 *
 * 扩展：
 * 1. 信号可以连接信号
 * 2. 一个信号可以连接多个槽函数
 * 3. 多个信号可以连接同一个槽函数
 * 4. 信号和槽函数的参数，类型必须一一对应
 * 5. 信号的参数个数，可以多于槽函数的参数个数
 *
 * Qt4 版本以前的信号和槽连接方式：
 * connect(teacher, SIGNAL(dragHall()), student, SLOT(unhappy()));
 * Qt4 写法优点：参数直观    缺点：类型不做检测
 * Qt5 以上版本支持 Qt4 版本写法，反之不行
 */

Widget::Widget(QWidget *parent)
    : QWidget(parent)
{
    //创建老师对象
    this->teacher = new Teacher;

    //创建学生对象
    this->student = new Student;

    //建立连接：老师拖堂，学生不开心
//    connect(teacher, &Teacher::dragHall, student, &Student::unhappy);

    //连接带参数的信号和槽
    void(Teacher:: *teacherSignal) (QString) = &Teacher::dragHall;
    void(Student:: *studentSolts) (QString) = &Student::unhappy;
    connect(teacher, teacherSignal, student, studentSolts);

//    this->finishClass();

    //创建按钮
    QPushButton *btn = new QPushButton ("下课了", this);
    //重置窗口大小
    this->resize(600, 400);
    //点击按钮触发下课
//    connect(btn, &QPushButton::clicked, this, &Widget::finishClass);

    //无参信号和槽连接
    void(Teacher:: *teacherSignal2) (void) = &Teacher::dragHall;
    void(Student:: *studentSolts2) (void) = &Student::unhappy;
    connect(teacher, teacherSignal2, student, studentSolts2);

    //信号连接信号
    connect(btn, &QPushButton::clicked, teacher, teacherSignal2);

    //Lamda表达式
    [=](){
        btn->setText("aaa");
    }();
}

void Widget::finishClass() {
    qDebug() << "亲爱滴同学们，下课了！";
//    emit this->teacher->dragHall();

    emit teacher->dragHall("学生叽叽喳喳");
}

Widget::~Widget()
{
    delete this->teacher;
    delete this->student;
}

