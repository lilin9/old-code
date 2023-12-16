#include <iostream>
#include "sales.h"

/*
    请基于下面这个名称空间编写一个由3个文件组成的程序：
    namespace SALES {
        const int QUARTERS = 4;
        strcut Sales {
            double sales[QUARTERS];
            double average;
            double max;
            double min;
        };

        //copies the lesser of 4 or n items from the array ar
        //to the sales member of s and computer ad stores the
        //average, maximum, and minimum values of the entered item;
        //remaining elements of sales, if any, set to 0
        void setSales(Sales &s, const double ar[], int n);

        //gathers sales for 4 quarters interactively, stores them
        //in the sales member of s and computers and stores the
        //average, maximum, and minumum values
        void setSales(Sales &s);

        //dispaly all information in structure s
        void showSales(const Sales &s);
    }

    第一个文件是一个头文件，其中包含名称空间。第二个文件是一个源代码文件，它对这个名称空间进行扩展，
    以提供这3个函数的定义。第三个文件声明两个Sales对象，并使用setSales()的交互式版本为一个结构提供
    值，然后使用setSales()的非交互式版本为另一个结构提供值。另外它还使用showSales()来显示这两个结
    构的内容。
*/

int main4() {
    using SALES::Sales;
    using SALES::setSales;
    using SALES::showSales;

    Sales sale1;
    Sales sale2;

    setSales(sale1);
    setSales(sale2, sale1.sales, 2);

    showSales(sale1);
    showSales(sale2);
    return 0;
}
