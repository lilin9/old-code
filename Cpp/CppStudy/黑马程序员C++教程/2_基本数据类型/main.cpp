#include <iostream>

int main2() {
    wchar_t b = 10;
    std::cout << b << std::endl;
    std::cout << sizeof(wchar_t) << std::endl;

    // typeof int feet;
    // feet b = 20;
    // std::cout << b << std::endl;

    // enum color {red, green, blue} c;
    // c = blue;
    // std::cout << c << std::endl;

    std::cout << "=================" << std::endl;

    const int A = 10;
    const int B = 20;
    int c = A + B;
    std::cout << A << std::endl;
    std::cout << B << std::endl;
    std::cout << c << std::endl;

    std::cout << "=================" << std::endl;

    unsigned one = 10;
    int two = 10;
    std::cout << (one == two) << std::endl;

    std::cout << "=================" << std::endl;

    short int i;
    unsigned short int j = 50000;
    i = j;
    std :: cout << i << "\n" << j << std::endl;
}