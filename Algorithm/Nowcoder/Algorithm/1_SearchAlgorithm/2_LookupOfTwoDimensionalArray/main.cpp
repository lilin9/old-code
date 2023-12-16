#include "iostream"
#include "vector"

using namespace std;

class Solution {
public:
    bool Find(int target, vector<vector<int> > array) {
        //参数判空
        if (array.empty() || array[0].empty()) {
            return false;
        }
        //只有一个参数
        if (array.size() == 1 && array[0].size() == 1) {
            return array[0][0] == target;
        }


        //获取头指针
        int left = 0;
        //获取尾指针
        int right = array.size() - 1;
        //获取矩阵的宽度
        int weight = array[0].size() - 1;

        //二分遍历最外层数组
        while (left <= right) {
            int mid = left + (right - left)/2;

            //判断 target 是否在当前数组中
            if (target < array[mid][0]) {
                right = mid - 1;
            } else if (target > array[mid][weight]) {
                left = mid + 1;
            } else {
                //在当前数组中查找 target
                int num = BinarySearch(array[mid], target);
                if (num == target) {
                    return true;
                } else {
                    //当前数组没有目标 target，避免对后续查找造成影响，将其删除
                    array.erase(array.begin() + mid);
                    right = array.size() - 1;
                }
            }
        }
        return false;
    }

    int BinarySearch(vector<int> array, int num) {
        if (array.empty())
            return 0;

        //获取头指针
        int left = 0;
        //尾指针
        int right = array.size() - 1;

        //循环遍历，二分查找
        while (left <= right) {
            //获取中间指针
            int mid = left + (right - left) / 2;

            //判断
            if (num < array[mid]) {
                right = mid - 1;
            } else if (num > array[mid]) {
                left = mid + 1;
            } else {
                return array[mid];
            }
        }
        return 0;
    }
};

int main2() {
    Solution solution;

//    vector<int> item1 = {1, 2, 8, 9};
//    vector<int> item2 = {2, 4, 9, 12};
//    vector<int> item3 = {4, 7, 10, 13};
//    vector<int> item4 = {6, 8, 11, 15};
//    vector<vector<int>> arr;
//    arr.push_back(item1);
//    arr.push_back(item2);
//    arr.push_back(item3);
//    arr.push_back(item4);
//
//    bool result = solution.Find(7, arr);  //true
//    bool result = solution.Find(3, arr);    //false

//    vector<int> item = {};
//    vector<vector<int>> arr;
//    arr.push_back(item);
//
//    bool result = solution.Find(16, arr);  //false

    vector<int> item = {1, 1};
    vector<vector<int>> arr;
    arr.push_back(item);

    bool result = solution.Find(2, arr);

    if (result) {
        cout << "True" << endl;
    } else {
        cout << "False" << endl;
    }

    return 0;
}