#include <iostream>
#include "vector"

using namespace std;

/*
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5,
 * 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */

//快慢双指针解法
int deleteNumForArray(vector<int> nums, int val) {
    //定义慢指针
    int slow = 0;

    //定义一个循环
    for (int quick = 0; quick < nums.size(); ++quick) {
        //判断当前元素是否等于 val
        if (val != nums[quick]) {
            nums[slow] = nums[quick];
            slow++;
        }
    }

    return slow;
}

int main2() {
//    vector<int> nums = {1, 2, 2, 3};
//    cout << deleteNumForArray(nums, 3) << endl;     //3

    vector<int> nums = {0,1,2,2,3,0,4,2};
    cout << deleteNumForArray(nums, 2) << endl;     //5

    return 0;
}
