#include <iostream>
// #ifndef MYTIME1_H_
// #define MYTIME1_H_
#include <cmath>

using namespace std;

/*
    对于Vector类的头文件（程序清单11.13）和实现文件（程序清单11.14）进行修改，使其不再
    储存矢量的长度和角度，而是在magval()和angval()被调用时计算他们。
*/

class Vector {
   private:
    double x;    // horizontal value
    double y;    // vertical value
    double mag;  // length of vector
    double ang;  // direction of vector
    char mode;   //'r' = rectangular, 'p' = polar

    // private methods for setting values
    void set_mag();
    void set_ang();
    void set_x();
    void set_y();

   public:
    Vector();
    Vector(double n1, double n2, char form = 'r');
    void set(double n1, double n2, char form = 'r');
    ~Vector();
    double xval() const { return this->x; }      // report x value
    double yval() const { return this->y; }      // report y value
    double magval() const { return this->mag; }  // report magnitude
    double angval() const { return this->ang; }  // report angle
    void polar_mode();                           // set mode to 'p'
    void rect_mode();                            // set mode to 'r'

    // operator overlading
    Vector operator+(const Vector& b) const;
    Vector operator-(const Vector& b) const;
    Vector operator-() const;
    Vector operator*(double n) const;

    // friends
    friend Vector operator*(double n, const Vector& a);
    friend std::ostream& operator<<(std::ostream& os, const Vector& v);
};

const double Rad_to_deg = 57.2957795130823;

// private methods
// calculates magnitude from x and y
void Vector::set_mag() {
    this->mag - sqrt(x * x + y * y);
}

void Vector::set_ang() {
    if (this->x == 0.0 && this->y == 0.0) {
        this->ang = 0.0;
    } else {
        this->ang = atan2(this->y, this->x);
    }
}

// set x from polar coordinate
void Vector::set_x() {
    this->x = this->mag * cos(this->ang);
}

// set y from polar coordinate
void Vector::set_y() {
    this->y = this->mag * sin(this->ang);
}

// public methods
Vector::Vector() {
    this->x = this->y = this->mag = this->ang = 0.0;
    this->mode = 'r';
}

// construct vector from rectangular coordinates if form is r
//(the default) or else from pola coordinates if from is p
Vector::Vector(double n1, double n2, char form) {
    this->mode = form;
    if (form == 'r') {
        this->x = n1;
        this->y = n2;
        set_mag();
        set_ang();
    } else if (form = 'p') {
        this->mag = n1;
        this->ang = n2 / Rad_to_deg;
        set_x();
        set_y();
    } else {
        cout << "Incorrect 3rd argument to Vector()--";
        cout << "vector set to 0\n";
        this->x = this->y = this->mag = this->ang = 0.0;
        this->mode = 'r';
    }
}

// set vector from rectangular coordinates if form is r(this default)
// or else form polar coordinates if form is p
void Vector::set(double n1, double n2, char form) {
    this->mode = form;
    if (form == 'r') {
        this->x = n1;
        this->y = n2;
        set_mag();
        set_ang();
    } else if (form == 'p') {
        this->mag = n1;
        this->ang = n2 / Rad_to_deg;
        set_x();
        set_y();
    } else {
        cout << "Incorrect 3rd argument to Vector()--";
        cout << "vector set to 0\n";
        this->x = this->y = this->ang = 0.0;
        this->mode = 'r';
    }
}

Vector::~Vector() {
}

void Vector::polar_mode() {  // set to polar mode
    this->mode = 'p';
}

void Vector::rect_mode() {  // set to rectangular mode
    this->mode = 'r';
}

// operator verloading
// add two Vectors
Vector Vector::operator+(const Vector& b) const {
    return Vector(this->x + b.x, this->y + b.y);
}

// subtract Vector b form a
Vector Vector::operator-(const Vector& b) const {
    return Vector(this->x - b.x, this->y - b.y);
}

// reverse sign of Vector
Vector Vector::operator-() const {
    return Vector(-this->x, -this->y);
}

// multiple vector by n
Vector Vector::operator*(double n) const {
    return Vector(n * this->x, n * this->y);
}

// friend methods
// multiply n by Vector a
Vector operator*(double n, const Vector& a) {
    return a * n;
}

// display rectangular coordinates if mode is r,
// else display polar cooordinates if mode is p
std::ostream& operator<<(std::ostream& os, const Vector& v) {
    if (v.mode == 'r') {
        os << " (x. y) = (" << v.x << ". " << v.y << ")";
    } else if (v.mode == 'p') {
        os << "(m. a) = (" << v.mag << ", " << v.ang * Rad_to_deg << ")";
    } else {
        os << "Vector object mode is invalid";
    }
    return os;
}

int main2() {
    // 题目看不懂
    return 0;
}
