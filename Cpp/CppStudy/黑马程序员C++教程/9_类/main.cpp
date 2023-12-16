#include <cstring>
#include <iostream>

using namespace std;

class Person {
 public:
  string name;
  string sex;

 public:
  // 无参构造
  Person() {}
  // 有参构造
  Person(string name, string sex) {
    this->name = name;
    this->sex = sex;
  }
  // 析构函数 [进行对象的回收]
  ~Person() { cout << "析构函数" << endl; }
  // 拷贝构造函数
  Person(const Person &person) {
    name = person.name;
    sex = person.sex;
  }

  void set(string name, string sex) {
    this->name = name;
    this->sex = sex;
  }

  void show() { cout << "姓名: " << name << "\n性别: " << sex << endl; }
};

class Student : public Person {
 public:
  string school;
  string hobby;

  void show() {
    cout << "姓名: " << name << endl;
    cout << "性别: " << sex << endl;
    cout << "爱好: " << this->hobby << endl;
    cout << "学校: " << this->school << endl;
  }
};

int main() {
  //   Person person;
  //   person.name = "tom";
  //   person.sex = "boy";
  Person person("tom", "boy");

  person.show();

  cout << endl;

  Person person1 = Person();
  person1.set("tony", "boy");
  person1.show();

  cout << endl;

  Student student;
  student.name = "小美";
  student.sex = "女";
  student.hobby = "追剧";
  student.school = "王村小学";
  student.show();
  return 0;
}
