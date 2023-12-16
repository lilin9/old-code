#include <iostream>
#include "unordered_set"

/*
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为 1，
 * 那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */

//这个函数用来获取一个数字的各个单数平方之和
int getSingularSum(int num) {
    if (num < 10) {
        return num * num;
    }

    //记录个位数平方和
    int sum = 0;
    while (num) {
        sum += (num % 10) * (num % 10);
        num = num / 10;
    }
    return sum;
}

bool isHappy(int n) {
    //n负数不要
    if (n <= 0) {
        return false;
    }

    //set容器
    std::unordered_set<int> set;
    int sum = n;

    while (sum != 1) {
        //获取个位数之和
        sum = getSingularSum(sum);
        //判断sum有没有重复
        if (set.find(sum) != set.end()) {
            return false;
        }
        //将sum存入set中
        set.insert(sum);
    }
    return true;
}

int main3() {
//    bool result = isHappy(19);  //true
    bool result = isHappy(2);  //false

    if (result) {
        std::cout << "true" << std::endl;
    } else {
        std::cout << "false" << std::endl;
    }
    return 0;
}