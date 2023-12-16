#include <iostream>
#include "string.h"

using namespace std;

struct Chaff {
    char dross[20];
    int slag;
};


int main3() {
    Chaff *chaffArr = new Chaff[2];

    Chaff chaff1;
    Chaff chaff2;
    strcpy(chaff1.dross, "abcd");
    strcpy(chaff2.dross, "edfg");
    chaff1.slag = 1;
    chaff2.slag = 2;

    chaffArr[0] = chaff1;
    chaffArr[1] = chaff2;

    for (int i = 0; i < 2; i++)
    {
        cout << chaffArr[i].dross << " " << chaffArr[i].slag << endl;
    }
    

    return 0;
}