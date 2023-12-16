#include "iostream"
#include "vector"

using namespace std;

class Solution {
public:
    int minNumberInRotateArray(vector<int> rotateArray) {
        //判断参数为空
        if (rotateArray.empty()) {
            return 0;
        }

        //如果数组只有一个值
        if (rotateArray.size() == 1) {
            return rotateArray[0];
        }

        //使用二分查找来做
        //获取头指针
        int left = 0;
        //获取尾指针
        int right = rotateArray.size() - 1;

        //循环
        while (left <= right) {
            //获取中间指针
            int mid = left + (right -left) / 2;

            //如果 mid 值大于 right 值
            if (rotateArray[mid] > rotateArray[right]) {
                left = mid + 1;
            } else if (rotateArray[mid] < rotateArray[right]) {
                //如果 mid 值小于 right 值
                right = mid;
            } else if (right == left) {
                //如果 right 等于 left，说明最小值就找到了
                return rotateArray[left];
            } else {
                right--;
            }
        }

        return 0;
    }
};

int main3() {
    Solution solution;

//    vector<int> arr{3, 4, 5, 1, 2}; //1
//    vector<int> arr{3, 100, 200, 3}; //3
    vector<int> arr{1, 2, 2, 2, 2, 2}; //1


    cout << solution.minNumberInRotateArray(arr) << endl;
    return 0;
}