#include <iostream>
#include <cstring>

using namespace std;

struct Book {
    int     id;
    string  title;
    float   price;
} book;


int main8() {
   book.id = 1;
   book.title = "《德道经》";
   book.price = 100.00;

   cout << "ID:" << book.id << endl;
   cout << "书名:" << book.title << endl;
   cout << "价格:" << book.price << endl;

   Book *pointer = &book;
   cout << "书名:" << pointer -> title << endl;
   return 0;
}
