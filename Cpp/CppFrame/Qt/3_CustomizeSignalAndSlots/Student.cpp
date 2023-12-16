#include "Student.h"
#include "QDebug"

Student::Student(QObject *parent): QObject(parent) {

}

void Student::unhappy() {
    qDebug() << "老师拖堂，学生不开心";
}

void Student::unhappy(QString speak) {
//    qDebug() << speak;
    qDebug() << speak.toUtf8().data();
}
