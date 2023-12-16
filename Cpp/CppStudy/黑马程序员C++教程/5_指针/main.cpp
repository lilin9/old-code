#include <iostream>

using namespace std;

void changeValue(int);
void changePointer(int *);

int main5() {
  int a = 10;

  std ::cout << "a的值等于 " << a << std ::endl;
  std ::cout << "a的地址等于 " << &a << std ::endl;

  std ::cout << "" << std ::endl;

  changeValue(a);
  std ::cout << "值传递后a的值 " << a << std ::endl;

  std ::cout << "" << std ::endl;

  changePointer(&a);
  std ::cout << "指针传递后a的值 " << a << std ::endl;

  return 0;
}

// 值传递改变数字
void changeValue(int num) {
  num = num + 1;
  std ::cout << "值传递 " << num << std ::endl;
}

// 指针传递改变数字
void changePointer(int *num) {
  *num = *num + 1;
  std ::cout << "指针传递 " << *num << std ::endl;
}
