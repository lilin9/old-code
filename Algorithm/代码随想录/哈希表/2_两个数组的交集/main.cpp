#include <iostream>
#include "vector"
#include "unordered_set"

/*
 * 给定两个数组，编写一个函数来计算它们的交集
 *
 * 示例1：
 * 输入：nums1 = [1, 2, 2, 1], nums2 = [2, 2]
 * 输出：[2]
 *
 * 示例2：
 * 输入：nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
 * 输出：[9, 4]
 *
 * 说明：输出结果中的每个元素一定是唯一的。我们可以不考虑输出结果的顺序
 */

std::vector<int> intersection(std::vector<int>& nums1, std::vector<int>& nums2) {
    if (nums1.empty() || nums2.empty()) {
        return std::vector<int>{};
    }

    //创建空的 unordered_set 容器，存放结果
    std::unordered_set<int> resultSet;
    //将 nums1 转换成 unordered_set
    std::unordered_set<int> numsSet(nums1.begin(), nums1.end());

    //遍历nums2
    for (int num: nums2) {
        //判断 nums2 中的元素是否在 numsSet 中存在
        if (numsSet.find(num) != numsSet.end()) {
            resultSet.insert(num);
        }
    }

    //清空 nums1 用来存放要返回的结果
    nums1.clear();
    nums1.assign(resultSet.begin(), resultSet.end());
    return nums1;
}

int main2() {
    std::vector<int> nums1 = {4, 9, 5};
    std::vector<int> nums2 = {9, 4, 9, 8, 4};    //9, 4
//    std::vector<int> nums1 = {1, 2, 2, 1};
//    std::vector<int> nums2 = {2, 2};    //2

    for (int item: intersection(nums1, nums2)) {
        std::cout << item << " ";
    }
    std::cout << std::endl;
    return 0;
}