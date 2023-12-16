#include "iostream"

using namespace std;

/*
 * 修改程序清单7.7中的3个数组处理函数，使之使用两个指针参数来表示区间。fill_array() 函数不返回
 * 实际读取了多少个数字，而是返回一个指针，该指针指向最后被填充的位置；其他的函数可以将该指针
 * 作为第二个参数，以标识数据结尾。
 */

const int Max = 5;

double* fillArray(double ar[], int limit);
void showArray(double ar[], const double* end);
void revalue(double r, double ar[], const double* end);

int main7() {
    double properties[Max];

    double* end = fillArray(properties, Max);
    showArray(properties, end);
    cout << "Enter revaluation factor: ";
    double factor;
    cin >> factor;
    revalue(factor, properties, end);
    showArray(properties, end);
    cout << "Done.\n";
    return 0;
}

double* fillArray(double ar[], int limit) {
    double temp;
    int i;
    for (i = 0; i < limit; ++i) {
        cout << "Enter value #" << (i+1)  << ": ";
        cin >> temp;
        if (!cin) {
            cin.clear();
            while (cin.get() != '\n') {
                continue;
            }
            cout << "Bad input: input process terminated.\n";
            break;
        } else if (temp < 0) {
            break;
        }
        ar[i] = temp;
    }
    return &ar[i];
}

//the following function can use, but not alter,
//the array whose address is ar
void showArray(double ar[], const double* end) {
    int count = 0;
    for (auto i = ar; i < end; ++i) {
        cout << "Property #" << ++count << ": $";
        cout << *i << endl;
    }

}

//multiplies each element of ar[] by r
void revalue(double r, double ar[], const double* end) {
    for (auto i = ar; i < end; ++i) {
        *i *= r;
    }
}