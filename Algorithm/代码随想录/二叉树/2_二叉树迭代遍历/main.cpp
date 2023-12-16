#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

/*
 * 使用迭代实现二叉树的前、中、后序遍历。
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

//前序遍历
vector<int> preorderTraversal(TreeNode *root) {
    //存放结果
    vector<int> result;
    //定义迭代栈
    stack<TreeNode*> sta;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //迭代遍历二叉树，栈为空时退出
    TreeNode *node;
    sta.push(root);
    while (!sta.empty()) {
        //取得栈内节点，并将其弹出
        node = sta.top();
        sta.pop();
        //将其值存入 result 数组
        result.push_back(node->val);

        //在节点值不为空的情况下，将其右左子节点入栈
        if (node->right) sta.push(node->right);
        if (node->left) sta.push(node->left);
    }
    return result;
}

//中序遍历
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    //定义迭代栈
    stack<TreeNode*> sta;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //迭代遍历二叉树，栈为空时退出
    TreeNode *node = root;
    //遍历二叉树
    while (node != nullptr || !sta.empty()) {
        //一直遍历到叶子节点
        if (node != nullptr) {
            //将节点入栈
            sta.push(node);
            //读取左节点
            node = node->left;
        } else {
            //取出栈顶节点
            node = sta.top();
            sta.pop();
            //将节点值存入数组
            result.push_back(node->val);
            //读取右节点
            node = node->right;
        }
    }
    return result;
}

//后序遍历
vector<int> postorderTraversal(TreeNode *root) {
    vector<int> result;
    //定义迭代栈
    stack<TreeNode*> sta;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //迭代遍历二叉树，栈为空时退出
    TreeNode *node;
    sta.push(root);
    while (!sta.empty()) {
        //取得栈内节点，并将其弹出
        node = sta.top();
        sta.pop();
        //将其值存入 result 数组
        result.push_back(node->val);

        //在节点值不为空的情况下，将其右左子节点入栈
        if (node->left) sta.push(node->left);
        if (node->right) sta.push(node->right);
    }
    //翻转数组
    reverse(result.begin(), result.end());
    return result;
}

int main2() {
    auto *root = new TreeNode(1);
    auto *node2 = new TreeNode(2);
    auto *node3 = new TreeNode(3);

    root->right = node2;
    node2->left = node3;

    vector<int> preorderResult = preorderTraversal(root);
    vector<int> inorderResult = inorderTraversal(root);
    vector<int> postorderResult = postorderTraversal(root);

    cout << "前序遍历: ";
    for (int item: preorderResult) {
        cout << item << " ";
    }
    cout << "\n中序遍历: ";
    for (int item: inorderResult) {
        cout << item << " ";
    }
    cout << "\n后序遍历: ";
    for (int item: postorderResult) {
        cout << item << " ";
    }
    cout << endl;
    return 0;
}