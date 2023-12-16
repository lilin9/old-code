#include <iostream>
#include <vector>
#include <deque>

/*
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * 进阶：你能在线性时间复杂度内解决此题吗？
 *
 * 示例：
 * 输入：nums = [1, 3, -1, -3, 5, 3, 6, 7]，和 k = 3
 * 输出：[3, 3, 5, 5, 6, 7]
 *
 * 提示：
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - 1 <= k <= nums.length
 */

using namespace std;

class MyQueue {
private:
    deque<int> data;
public:
    //如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
    void pop(int value) {
        if (value == this->data.front()) {
            this->data.pop_front();
        }
    }

    //如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，
    //直到push元素的数值小于等于队列入口元素的数值为止
    void push(int value) {
        //如果队列 back 的数小于 value，将其弹出，直到 back 大于 value
        while (!this->data.empty() && this->data.back() < value) {
            this->data.pop_back();
        }
        this->data.push_back(value);
    }

    int front() {
        //每次都返回队列首元素，即当前队列的最大值
        return this->data.front();
    }
};

vector<int> maxSlidingWindow(vector<int> &nums, int k) {
    vector<int> result;
    //判空
    if (nums.empty() || k == 0) return result;
    //如果 nums 只有一个数，k 的值也为 1
    if (nums.size() == 1 && k == 1) {
        result.push_back(nums[k - 1]);
        return result;
    }

    //定义自定义队列
    MyQueue queue;

    //将前 k 个数放入队列
    for (int i = 0; i < k; ++i) {
        queue.push(nums[i]);
    }
    //取得前 k 个数中的最大值
    result.push_back(queue.front());

    //遍历 k 之后的数
    for (int i = k; i < nums.size(); ++i) {
        //将当前 nums 的数放入移动窗口
        queue.push(nums[i]);
        //将第 i-k 的数移出移动窗口
        queue.pop(nums[i - k]);
        //取得当前移动窗口的最大值
        result.push_back(queue.front());
    }

    return result;
}

int main6() {
    vector<int> nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;  //3, 3, 5, 5, 6, 7

    vector<int> result = maxSlidingWindow(nums, k);
    for (int item: result) {
        cout << item << " ";
    }
    cout << endl;
    return 0;
}