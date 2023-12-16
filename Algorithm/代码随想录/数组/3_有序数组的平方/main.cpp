#include <iostream>
#include "vector"

/*
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：输入：nums = [-4,-1,0,3,10] 输出：[0,1,9,16,100] 解释：平方后，数组变为 [16,1,0,9,100]，
 * 排序后，数组变为 [0,1,9,16,100]
 *
 * 示例 2: 输入：nums = [-7,-3,2,3,11] 输出：[4,9,9,49,121]
 */

std::vector<int> getNewArray(std::vector<int> &nums) {
    //定义三个指针
    int head = 0;
    int tail = nums.size()-1;
    int len = nums.size();
    //定义 result 数组，储存结果
    std::vector<int> result(len);


    int left;
    int right;
    while(head <= tail) {
        left = nums[head] * nums[head];
        right = nums[tail] * nums[tail];

        if (left <= right) {
            result[--len] = right;
            tail--;
        } else {
            result[--len] = left;
            head++;
        }
    }

    return result;
}

int main3() {
//    std::vector<int> nums = {-4, -1, 0, 3, 10}; //0,1,9,16,100
    std::vector<int> nums = {-7,-3,2,3,11};   //4,9,9,49,121


    for (auto item: getNewArray(nums)) {
        std::cout << item << " ";
    }
    std::cout << std::endl;
}