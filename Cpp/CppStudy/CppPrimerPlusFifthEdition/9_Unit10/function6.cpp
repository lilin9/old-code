#include <iostream>
#include "string.h"
using namespace std;

/*
    下面是一个类声明：
    class Move {
    private:
        double x;
        double y;
    public:
        Move(double a = 0, double b = 0);   //sets x, y to a, b
        showMove() const;                   //shows current x, y values
        //this function adds x of m to x of invoking object to get new x,
        //adds y of m to y of invoking object to get new y, creates a new
        //move object initialized to new x, y values and returns it
        Move add(const Move &m) const;
        reset(double a = 0, double b = 0);  //resets x, y to a, b
    };
    请提供成员函数的定义和测试这个类的程序
*/

class Move {
   private:
    double x;
    double y;

   public:
    Move(double a = 0, double b = 0) {
        this->x = a;
        this->y = b;
    }

    void showMove() const {
        cout << "x = " << this->x << ", y = " << this->y << endl;
    }

    Move add(const Move &m) const {
        Move newMove(this->x + m.x, this->y + m.y);
        return newMove;
    }

    void reset(double a = 0, double b = 0) {
        this->x = a;
        this->y = b;
    }
};

int main6() {
    Move move(2, 3);
    move.showMove();

    Move newMove = move.add(Move(1, 2));
    newMove.showMove();

    move.reset(3, 5);
    move.showMove();

    return 0;
}
