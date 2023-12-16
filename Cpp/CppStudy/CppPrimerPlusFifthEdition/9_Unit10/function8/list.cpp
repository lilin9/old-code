#include "list.h"

template<class T>
List<T>::List() {}

template<class T>
List<T>::List(int size) {
    this->size = size;
    this->values = new T[size];
}

template<class T>
List<T>::~List() {
    delete this->values;
}

template<class T>
void List<T>::add(T val) {
    // 判断元素个数是否超过最大容量
    if (this->length > this->size) {
        return;
    }

    this->length++;
    this->values[this->length] = val;
}

template<class T>
bool List<T>::isEmpty() {
    if (this->length == -1) {
        return true;
    } else {
        return false;
    }
}

template<class T>
bool List<T>::isFull() {
    if (this->length == this->size) {
        return true;
    } else {
        return false;
    }
}

template<class T>
T List<T>::get(int index) {
    if (index > this->length) {
        return;
    }

    return this->values[index];
}