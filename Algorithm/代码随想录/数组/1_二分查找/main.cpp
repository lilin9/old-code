#include <iostream>
#include <vector>

/*
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * 示例1：
 *  输入: nums = [-1,0,3,5,9,12], target = 9
 *  输出: 4
 *  解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例2：
 *  输入: nums = [-1,0,3,5,9,12], target = 2
 *  输出: -1
 *  解释: 2 不存在 nums 中因此返回 -1
 *
 * 提示：
 * 1. 你可以假设 nums 中的所有元素是不重复的。
 * 2. n 将在 [1, 10000]之间。
 * 3. nums 的每个元素都将在 [-9999, 9999]之间。
 */

using namespace std;

int get(vector<int> &nums, int target) {
    int start = 0;
    int end = (int) nums.size() - 1;
    int result = -1;

    while (start <= end) {
        int middle = (end + start) / 2;
        if (nums[middle] == target) {
            result = middle;
            break;
        } else if (nums[middle] > target) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
    }

    return result;
}

int main1() {
    vector<int> nums = {-1, 0, 3, 5, 9, 12};
    int target = 9;
    cout << "index = " << get(nums, target) << endl;
    return 0;
}
