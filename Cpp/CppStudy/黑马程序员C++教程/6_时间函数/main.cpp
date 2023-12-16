#include <iostream>
#include <ctime>

using namespace std;

int main6() {
   //获取当前系统的时间和日期
   time_t now = time(0);
   cout << now << endl;

    //使用 tm 结构格式化时间
    tm* ltm = localtime(&now);
    
    int year = ltm -> tm_year;
    int month = ltm -> tm_mon;
    int day = ltm -> tm_mday;
    int hour = ltm -> tm_hour;
    int min = ltm -> tm_min;
    int second = ltm -> tm_sec;

    cout << "Year: " << 1900 + year << endl;
    cout << "Month: " << 1 + month << endl;
    cout << "Day: " << day << endl;
    cout << "Hour: " << hour << endl;
    cout << "Min: " << min << endl;
    cout << "Second: " << second << endl;
   return 0;
}
