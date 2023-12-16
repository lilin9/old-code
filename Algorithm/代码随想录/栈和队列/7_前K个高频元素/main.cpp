#include <iostream>
#include <vector>
#include <map>
#include <queue>

/*
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * - 输入: nums = [1,1,1,2,2,3], k = 2
 * - 输出: [1,2]
 *
 * 示例 2:
 * - 输入: nums = [1], k = 1
 * - 输出: [1]
 *
 * 提示：
 * - 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * - 你的算法的时间复杂度必须优于 $O(n \log n)$ , n 是数组的大小。
 * - 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * - 你可以按任意顺序返回答案。
 */

using namespace std;

//计算元素在数组中出现的频率
map<int, int> calFrequency(vector<int> &nums) {
    map<int, int> result;
    //遍历 nums
    for (int item: nums) {
        result[item]++;
    }
    return result;
}

//重载运算符
class customOperator {
public:
    bool operator()(const pair<int, int> firstPair, const pair<int, int> secondPair) {
        return firstPair.second > secondPair.second;
    }
};

//使用 小顶堆优先级队列 对频率进行排序
vector<int> sort(map<int, int> &frequencyMap, int k) {
    //创建一个小顶堆优先级队列
    priority_queue<pair<int, int>, vector<pair<int, int>>, customOperator> priorityQueue;
    //遍历
    for (pair<int, int> item: frequencyMap) {
        priorityQueue.push(item);
        //如果队列里面的元素大于 k，就弹出一个元素
        if (priorityQueue.size() > k) {
            priorityQueue.pop();
        }
    }

    //找出队列中前 k 个最大的元素
    vector<int> result(k);
    for (int i = k-1; i >= 0 ; --i) {
        result[i] = priorityQueue.top().first;
        priorityQueue.pop();
    }
    return result;
}

vector<int> topKFrequent(vector<int> &nums, int k) {
    //存放要返回的结果数组
    vector<int> result;

    //判空
    if (nums.empty()) return result;
    //如果 nums 元素个数为 1，且 k 为 1
    if (nums.size() == 1 && k == 1) return nums;

    //取得数组中每个数出现的频率
    map<int, int> frequencyMap = calFrequency(nums);
    //根据频率进行排序，并返回
    return sort(frequencyMap, k);
}

int main7() {
    vector<int> nums = {1, 1, 1, 2, 2, 3};
    int k = 2;  //1,2


    for (int item: topKFrequent(nums, k)) {
        cout << item << " ";
    }
    cout << endl;
    return 0;
}