#include <iostream>
#include <vector>
#include <algorithm>

/*
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意： 答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
 */

std::vector<std::vector<int>> threeSum(std::vector<int> &nums) {
    //判空
    if (nums.empty()) {
        return std::vector<std::vector<int>>{};
    }

    //存放结果
    std::vector<std::vector<int>> result;

    //对数组进行升序排序
    std::sort(nums.begin(), nums.end());

    //定义两个指针
    int left = 0;
    int right = 0;

    for (int i = 0; i < nums.size(); i++) {
        //如果第一个元素大于 0，则任何三个元素相加都不可能等于 0
        if (nums[i] > 0) {
            return result;
        }

        //对 a 进行去重
        if (i >0 && nums[i] == nums[i - 1]) {
            continue;
        }

        left = i + 1;
        right = nums.size() - 1;
        //遍历 i 到 nums.size()-1 区间里的元素，查找符合条件的三元组
        while (left < right) {
            if (nums[i] + nums[left] + nums[right] < 0) {
                left++;
            } else if (nums[i] + nums[left] + nums[right] > 0) {
                right--;
            } else {
                result.push_back(std::vector<int>{nums[i], nums[left], nums[right]});

                //对 a、b 去重
                while (left < right && nums[right] == nums[right - 1]) {
                    right--;
                }
                while (left < right && nums[left] == nums[left + 1]) {
                    left++;
                }

                //left 和 right 同时收缩
                left++;
                right--;
            };
        }
    }

    return result;
}

int main6() {
    std::vector<int> nums = {-1, 0, 1, 2, -1, -4};  //[-1, 0, 1], [-1, -1, 2]

    auto result = threeSum(nums);
    for (std::vector<int> num: result) {
        std::cout << "[";
        for (int item: num) {
            std::cout << item << " ";
        }
        std::cout << "] ";
    }
    std::cout << std::endl;
    return 0;
}