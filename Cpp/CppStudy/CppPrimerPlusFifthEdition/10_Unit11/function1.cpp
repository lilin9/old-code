#include <iostream>
#ifndef MYTIME1_H_
#define MYTIME1_H_

using namespace std;

/*
    修改程序清单11.5，使之将一系列连续的随机漫步者位置写入到文件中。对于每个位置，用步号进行标识。
    另外，让程序将初始条件（目标距离和步长）以及结果小结写入到该文件中。该文件的内容与下面类似：
    Target Distance: 100, Step Size: 20
    0: (x, y) = (0, 0)
    1: (x, y) = (-11.4715, 16.383)
    2: (x, y) = (-8.68807, -3.42232)
    ...
    26: (x, y) = (42.2919, -78.2594)
    27: (x, y) = (58.6749, -89.7309)
    After 27 setps, the subject has the following location:
    (x, y) = (58.6749, -89.7309)
        or
    (m, a) = (107.212, -56.8194)
    Average outward distance per step = 3.97081
*/

class Time {
    private:
    int hours;
    int minutes;

    public:
    Time();
    Time(int h, int m=0);
    void AddMin(int m);
    void AddHr(int h);
    void Reset(int h = 0, int m = 0);
    Time operator +(const Time &t) const;
    void Show() const;
};

Time::Time() {
    this->hours = this->minutes = 0;
}

Time::Time(int h, int m=0) {
    this->hours = h;
    this->minutes = m;
}

void Time::AddMin(int m) {
    this->minutes += m;
    this->hours += m / 60;
    this->minutes %= 60;
}

void Time::AddHr(int h) {
    this->hours += h;
}

void Time::Reset(int h = 0, int m = 0) {
    this->hours = h;
    this->minutes = m;
}

Time Time::operator +(const Time &t) const {
    Time sum;
    sum.minutes = this->minutes + t.minutes;
    sum.hours = this->hours + t.hours + sum.minutes / 60;
    sum.minutes %= 60;
    return sum;
}

void Time::Show() const {
    cout << this->hours << " hours. " << this->minutes << " minutes" << endl;
}

int main1() {
    //题目看不懂
    return 0;
}
