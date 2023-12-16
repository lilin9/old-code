#include <iostream>
#include "vector"

/*
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 示例:
 * 输入: 3
 * 输出: [ [ 1, 2, 3 ], [ 8, 9, 4 ], [ 7, 6, 5 ] ]
 */

std::vector<std::vector<int>> generateMatrix(int n) {
    //i,j分别表示矩阵的x，y轴
    int i = 0;
    int j = 0;
    //用来计数
    int num = 1;
    //记录矩阵填充的层数
    int count = 0;
    //返回的结果
    std::vector<std::vector<int>> result(n, std::vector<int>(n, 0));

    //遍历 n^2 的所有元素
    while (num < n * n) {
        count++;
        //第一层循环，用来遍历矩阵上下左右四条边的上
        while (j < n - count) {
            result[i][j] = num++;
            j++;
        }
        //第二层循环，用来遍历矩阵上下左右四条边的右
        while (i < n - count) {
            result[i][j] = num++;
            i++;
        }
        //第三层循环，用来遍历矩阵上下左右四条边的下
        while (j > count - 1) {
            result[i][j] = num++;
            j--;
        }
        //第四层循环，用来遍历矩阵上下左右四条边的左
        while (i > count - 1) {
            result[i][j] = num++;
            i--;
        }
        //更改i，j的起点坐标
        i++;
        j++;
    }

    //如果 n 是奇数，给矩阵最中心值填充值
    if (n % 2 != 0) {
        result[n / 2][n / 2] = n * n;
    }

    return result;
}

int main5() {
    //[[1,2,3],[8,9,4],[7,6,5]]
    std::vector<std::vector<int>> result = generateMatrix(3);

    for (const auto& item: result) {
        std::cout << "[";
        for(auto ite: item) {
            std::cout << ite << " ";
        }
        std::cout << "] ";
    }
    std::cout << std::endl;

    return 0;
}