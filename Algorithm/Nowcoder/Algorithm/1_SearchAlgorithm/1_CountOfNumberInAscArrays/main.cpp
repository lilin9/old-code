#include <iostream>
#include "vector"

using namespace std;

class Solution {
public:
    int GetNumberOfK(vector<int> data, int k) {
        //如果参数为空
        if (data.empty()) {
            return 0;
        }

        //如果参数只有一个值
        if (data.size() == 1) {
            return data[0] == k ? 1 : 0;
        }

        return BinarySearch(data, k + 0.5) - (BinarySearch(data, k - 0.5));
    }

    //使用二分查找，找到数出现的位置
    int BinarySearch(vector<int> data, float num) {
        //左指针
        int left = 0;
        //右指针
        int right = data.size() - 1;

        //1, 2, 3, 3, 3, 4, 5
        while (left <= right) {
            //获取中间指针
            int mid = left + (right - left) / 2;

            //判断中间指针指向的值和 num 的大小
            if (num < data[mid]) {
                right = mid - 1;
            }
            if (num > data[mid]) {
                left = mid + 1;
            }
        }

        return left;
    }
};

int main1() {
    vector<int> data = {1, 2, 3, 4, 5};
    Solution solution;

    cout << solution.GetNumberOfK(data, 3) << endl;

//    vector<int> data = {1, 2, 3, 4, 5};
//    Solution solution;
//
//    cout << solution.GetNumberOfK(data, 6) << endl;

//    vector<int> data = {3, 3, 3, 3};
//    Solution solution;
//
//    cout << solution.GetNumberOfK(data, 3) << endl;

//    vector<int> data = {3, 3, 3, 3, 4, 5};
//    Solution solution;
//
//    cout << solution.GetNumberOfK(data, 3) << endl;

//    vector<int> data = {1, 2, 3, 3, 3, 3};
//    Solution solution;
//
//    cout << solution.GetNumberOfK(data, 3) << endl;

    return 0;
}
