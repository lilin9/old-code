#include <iostream>
#include <vector>

/*
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0。
 *
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 提示：
 * - 1 <= target <= 10^9
 * - 1 <= nums.length <= 10^5
 * - 1 <= nums[i] <= 10^5
 */


//滑动窗口解法
int get(std::vector<int> &nums, int target) {
    //sum用来记录滑动窗口内值的和
    int sum = 0;
    //定义滑动窗口的起始指针
    int start = 0;
    //记录滑动窗口的长度
    int length;
    //最终返回的结果
    int result = INT32_MAX;

    //遍历数组
    for (int i = 0; i < nums.size(); ++i) {
        sum += nums[i];

        while (sum >= target) {
            length = i - start + 1;
            result = result < length ? result : length;

            sum -= nums[start++];
        }
    }

    return result == INT32_MAX ? 0 : result;
}

int main4() {
    std::vector<int> nums = {2, 3, 1, 2, 4, 3};
    int s = 7;  //2

    std::cout << get(nums, s) << std::endl;
    return 0;
}