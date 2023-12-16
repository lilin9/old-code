#include <iostream>

using namespace std;

class Animal {
   public:
    virtual void speak() {
        cout << "动物会叫" << endl;
    }
};

class Cat : public Animal {
   public:
    void speak() {
        cout << "猫咪在叫" << endl;
    }
};

class Dog : public Animal {
   public:
    void speak() {
        cout << "狗子在叫" << endl;
    }
};

void doSpeak(Animal& animal) {
    animal.speak();
}

int main14() {
    Animal animal;
    Cat cat;
    Dog dog;
    doSpeak(cat);
    doSpeak(dog);
    doSpeak(animal);

    return 0;
}
