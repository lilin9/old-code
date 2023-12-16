#include <iostream>

using namespace std;

class Hourse {
    //firend: 可以修改类的私有属性
    friend void updatePrivate(Hourse* hourse);

   public:
    string parlor;  // 客厅
   private:
    string room;  // 卧室

   public:
    Hourse() {
        this->parlor = "客厅";
        this->room = "卧室";
    }
};

void updatePrivate(Hourse* hourse) {
    cout << hourse->parlor << endl;
    cout << hourse->room << endl;
}

int test() {
    cout << "" << endl;
}

int main11() {
    Hourse hourse;
    updatePrivate(&hourse);
    return 0;
}
