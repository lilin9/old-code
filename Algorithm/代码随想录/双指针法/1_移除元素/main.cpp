#include <iostream>
#include <vector>

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
 */

int removeElement(std::vector<int> &nums, int val) {
    //判空
    if (nums.empty()) return 0;

    //首尾指针
    int left = 0;
    int right = nums.size() - 1;

    while (left <= right) {
        //找到左边等于 val 的值
        while (left <= right && nums[left] != val) left++;
        //找到右边不等于 val 的值
        while (left <= right && nums[right] == val) right--;

        //右边不等于 val 的值覆盖左边等于 val 的值
        if (left < right) {
            nums[left++] = nums[right--];
        }
    }

    return left;
}

int main1() {
//    std::vector<int> nums = {3, 2, 2, 3};
//    int val = 3;    //2, [2, 2]

//    std::vector<int> nums = {0, 1, 2, 2, 3, 0, 4, 2};
//    int val = 2;    //5, [0, 1, 3, 0, 4]

//    std::vector<int> nums = {3, 3};
//    int val = 3;    //0, []

//    std::vector<int> nums = {3};
//    int val = 3;    //0, []

    std::vector<int> nums = {3};
    int val = 4;    //1, [3]

    int result = removeElement(nums, val);

    std::cout << result << std::endl;
    for (int i = 0; i < result; ++i) {
        std::cout << nums[i] << " ";
    }
    std::cout << std::endl;
    return 0;
}