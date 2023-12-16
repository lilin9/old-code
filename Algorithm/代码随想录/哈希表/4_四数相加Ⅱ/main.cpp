#include <iostream>
#include "vector"
#include "unordered_map"

/*
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，
 * 使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 *
 * 例如:
 * 输入:
 * - A = [ 1, 2]
 * - B = [-2,-1]
 * - C = [-1, 2]
 * - D = [ 0, 2]
 *
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * - (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * - (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
using namespace std;

int fourSumCount(vector<int> &nums1, vector<int> &nums2, vector<int> &nums3, vector<int> &nums4) {
    //判空
    if (nums1.empty() || nums2.empty() || nums3.empty() || nums4.empty()) {
        return 0;
    }

    //定义一个 unordered_map，用来存放 nums1 和 nums2 遍历相加的结果
    //key 存放 nums1 和 nums2 相加的结果，value 存放其出现的次数
    unordered_map<int, int> map;

    //记录 nums1 和 nums2 中的元素和
    for (int num1: nums1) {
        for (int num2: nums2) {
            map[num1 + num2]++;
        }
    }

    //记录 num1+num2+num3+num4=0 出现的次数
    int count = 0;
    int temp = 0;

    for (int num3: nums3) {
        for (int num4: nums4) {
            temp = num3 + num4;
            //如果 num1+num2+num3+num4=0
            if (map.find(0 - temp) != map.end()) {
                count += map.at(0 - temp);
            }
        }
    }

    return count;
}

int main4() {
    vector<int> nums1 = {0};
    vector<int> nums2 = {0};
    vector<int> nums3 = {0};
    vector<int> nums4 = {0}; //1

//    vector<int> nums1 = {1, 2};
//    vector<int> nums2 = {-2,-1};
//    vector<int> nums3 = {-1, 2};
//    vector<int> nums4 = {0, 2}; //2

    cout << fourSumCount(nums1, nums2, nums3, nums4) << endl;
    return 0;
}