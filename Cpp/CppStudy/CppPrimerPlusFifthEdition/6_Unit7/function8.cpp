#include "iostream"

using namespace std;

/*
 * 这个练习让您编写处理数组和结构的函数。下面是程序的框架，请提供其中描述的函数，以完成该程序、
 * #include <iostream>
 * using namespace std;;
 *
 * const int SLEN = 30;
 * struct student {
 *      char fullname[SLEN];
 *      char hobby[SLEN];
 *      int ooplevel;
 * };
 * // getinfo() has two arguments: a pointer to the first element of
 * // an array of student structures and an int representing the
 * // number of element of the array. The function solicits and
 * // stores data about students. It terminates input upon filling
 * // the array or upon encountering a blank line for the student
 * // name. The function returns the actual number of array elements
 * // filled.
 * // getinfo() 有两个参数：一个指向学生结构数组第一个元素的指针和一个表示数组元素数的整数。
 * // 该函数请求并存储有关学生的数据。 它在填充数组或遇到学生的空白行时终止输入姓名。 该函
 * // 数返回实际填充的数组元素数。
 * int getinfo(student pa[], int n);
 *
 * // display1() takes a student structure as an argument
 * // and displays its contents
 * // display1() 将学生结构作为参数并显示其内容
 * void display1(student st);
 *
 * // display2() takes the address of student structure as an
 * // argument and displays the structure's contents
 * // display2() 将 student 结构的地址作为参数并显示结构的内容
 * void display2(const student *ps);
 *
 * // display3() takes the address of the first element of an array
 * // of student structures and the number of array elements as
 * // arguments and displays the contents of the structures
 * // display3() 将学生结构数组的第一个元素的地址和数组元素的数量作为参数并显示结构的内容
 * void display3(const student pa[], int n);
 *
 * int main() {
 *      cout << "Enter class size: ";
 *      int class_size;
 *      cin >> class_size;
 *      while(cin.get() != '\n') {
 *          continue;
 *      }
 *      student *ptr_stu = new student[class_size];
 *      int entered = getinfo(ptr_stu, class_size);
 *      for (int i = 0; i< entered; i++) {
 *          display1(ptr_stu[i]);
 *          display2(&ptr_stu[1]);
 *      }
 *      display3(ptr_stu, entered);
 *      delete [] ptr_stu;
 *      cout << "Done\n";
 *      return 0;
 * }
 */
const int SIZE = 30;

struct Student {
    char fullname[SIZE];
    char hobby[SIZE];
    int oopLevel;
};

int getInfo(Student studentArr[], int count) {
    int i;
    for (i = 0; i < count; ++i) {
        Student student;
        cout << "\nEnter fullname of student(empty input to quit): ";
        cin.getline(student.fullname, SIZE);

        if (student.fullname[0] == '\0') {
            break;
        }

        cout << "Enter hobby of student: ";
        cin.getline(student.hobby, SIZE);
        cout << "Enter oopLevel of student: ";
        cin >> student.oopLevel;
        cin.ignore();
        studentArr[i] = student;
    }
    return i;
}

void display1(Student student) {
    cout << "fullname of student is " << student.fullname << endl;
    cout << "hobby of student is " << student.hobby << endl;
    cout << "level of student is " << student.oopLevel << endl;
}

void display2(const Student *student) {
    cout << "fullname of student is " << student->fullname << endl;
    cout << "hobby of student is " << student->hobby << endl;
    cout << "level of student is " << student->oopLevel << endl;
}

void display3(const Student studentArr[], int count) {
    for (int i = 0; i < count; ++i) {
        cout << "Student #" << i+1 << ": " << endl;
        cout << "fullname: " << studentArr[i].fullname
        << ", hobby: " << studentArr[i].hobby << ", level: " << studentArr[i].oopLevel << endl;
    }
}

int main8() {
    Student studentArr[5];
    int count = getInfo(studentArr, 5);
    display1(studentArr[0]);
    cout << endl;

    display2(&studentArr[0]);
    cout << endl;

    display3(studentArr, count);
    return 0;
}