#include <iostream>

using namespace std;

int main3()
{
   int i = 0;

    for (; ; )
    {
        if (++i > 1000)
        {
            break;
        }

        std::cout << "这是一个循环" << std::endl;
        
    }
    
   return 0;
}
