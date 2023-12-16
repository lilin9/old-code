#include <iostream>
#include "sales.h"

namespace SALES {
void setSales(Sales& s, const double ar[], int n) {
    double min = ar[0];
    double max = ar[0];
    double average = 0;
    for (int i = 0; i < n; i++) {
        if (min > ar[i]) {
            min = ar[i];
        }
        if (max < ar[i]) {
            max = ar[i];
        }
        average += ar[i];

        s.sales[i] = ar[i];
    }
    s.max = max;
    s.min = min;
    s.average = average / n;
}

using std::cin;
using std::cout;
using std::endl;

void setSales(Sales& s) {
    double sale = 0;
    for (int i = 0; i < 4; i++) {
        cout << "Please enter your sales for the 4 quarters: ";
        cin >> sale;
        s.sales[i] = sale;
    }

    double min = s.sales[0];
    double max = s.sales[0];
    double average = 0;
    for (auto item : s.sales) {
        if (min > item) {
            min = item;
        }
        if (max < item) {
            max = item;
        }
        average += item;
    }

    s.min = min;
    s.max = max;
    s.average = average / 4;
}

void showSales(const Sales& s) {
    cout << "Four quarters of sales is: " << endl;
    for(auto item: s.sales) {
        cout << item << " ";
    }
    cout << "\nMinimum sales amount is: " << s.min << endl;
    cout << "Maximum sales amount is: " << s.max << endl;
    cout << "Average sales quota is: " << s.average << endl << endl;
}
}  // namespace SALES