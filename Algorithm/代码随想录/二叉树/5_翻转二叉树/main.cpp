#include <iostream>
#include <vector>
#include <queue>

/*
 * 翻转一棵二叉树。
 *
 * 示例1：
 * 输入：4, 2, 7, 1, 3, 6, 9
 * 输出：4, 7, 2, 9, 6, 3, 1
 *
 * 示例2：
 * 输入：2, 1, 3
 * 输出：2, 3, 1
 *
 * 示例3：
 * 输入：[]
 * 输出：[]
 */

using namespace std;

struct TreeNode {
    int val;

    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

TreeNode *invertTree(TreeNode *root) {
    //root 为空直接退出
    if (root == nullptr) return root;

    //交换左右子节点
    swap(root->left, root->right);
    //递归遍历左右子树
    invertTree(root->left);
    invertTree(root->right);

    return root;
}

void levelOrder(TreeNode *root) {
    vector<vector<int>> result;
    queue<TreeNode *> que;

    if (root == nullptr) return;

    que.push(root);
    //记录二叉树每一层的大小
    int size = 0;
    //记录每一次循环，从队列里读取到的二叉树节点
    TreeNode *node;
    //循环遍历，直到队列为空
    while (!que.empty()) {
        vector<int> level;
        //取得当前二叉树层次的大小
        size = que.size();
        //循环取出当前层的节点
        for (int i = 0; i < size; ++i) {
            //取出队列首节点
            node = que.front();
            que.pop();
            //将节点值存入数组
            level.push_back(node->val);
            //将当前节点的左右子节点存入队列
            if (node->left != nullptr) que.push(node->left);
            if (node->right != nullptr) que.push(node->right);
        }
        //将 level 数组放入 result 数组
        result.push_back(level);
    }

    for (vector<int> level: result) {
        for (int item: level) {
            cout << item << " ";
        }
    }
    cout << endl;
}

int main5() {
//    TreeNode *root = new TreeNode(4);
//    TreeNode *node2 = new TreeNode(2);
//    TreeNode *node7 = new TreeNode(7);
//    TreeNode *node1 = new TreeNode(1);
//    TreeNode *node3 = new TreeNode(3);
//    TreeNode *node6 = new TreeNode(6);
//    TreeNode *node9 = new TreeNode(9);
//
//    root->left = node2;
//    root->right = node7;
//    node2->left = node1;
//    node2->right = node3;
//    node7->left = node6;   //输出：4, 2, 7, 1, 3, 6, 9
//    node7->right = node9;   //输入：4, 7, 2, 9, 6, 3, 1


    TreeNode *root = new TreeNode(2);
    TreeNode *node1 = new TreeNode(1);
    TreeNode *node3 = new TreeNode(3);

    root->left = node1;   //输入：2, 1, 3
    root->right = node3;  //输出：2, 3, 1

    levelOrder(root);
    root = invertTree(root);
    levelOrder(root);
    return 0;
}