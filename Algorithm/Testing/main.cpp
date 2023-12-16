#include <iostream>
#include <vector>
#include <queue>

/*
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3, 9, 20, null, null, 15, 7]
 * 返回其层次遍历结果：[[3], [9, 20], [15, 7]]
 */

using namespace  std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

vector<vector<int>> levelOrder(TreeNode* root) {
    vector<vector<int>> result;
    queue<TreeNode*> que;

    if (root == nullptr) return result;
    if (root->left == nullptr && root->right == nullptr) {
        vector<int> item(root->val);
        result.push_back(item);
        return result;
    }

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

    return result;
}

int main() {
    auto *root = new TreeNode(3);
    auto *node9 = new TreeNode(9);
    auto *node20 = new TreeNode(20);
    auto *node15 = new TreeNode(15);
    auto *node7 = new TreeNode(7);

    root->left = node9;
    root->right = node20;
    node20->left = node15;
    node20->right = node7;  //[[3], [9, 20], [15, 7]]

    vector<vector<int>> result = levelOrder(root);
    for (const vector<int>& level: result) {
        for (int item: level) {
            cout << item << " ";
        }
        cout << endl;
    }

    return 0;
}