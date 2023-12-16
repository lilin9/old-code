#include <iostream>
#include <vector>
#include <stack>

/*
 * 统一迭代法中二叉树前中后序遍历的风格
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
    vector<int> result;
    //定义迭代栈
    stack<TreeNode *> sta;

    //将根节点 push 进栈
    if (root != nullptr) sta.push(root);
    //遍历二叉树
    TreeNode *node;
    while (!sta.empty()) {
        //取得栈顶节点
        node = sta.top();
        if (node != nullptr) {
            //将当前元素弹出
            sta.pop();

            //将右节点 push 进队列
            if (node->right) sta.push(node->right);

            //添加左节点
            if (node->left) sta.push(node->left);

            //添加中节点
            sta.push(node);
            //中节点处理过，添加一个处理过的标记
            sta.push(nullptr);
        } else {    //遇到空节点时
            //将空节点弹出
            sta.pop();
            //取得中节点
            node = sta.top();
            sta.pop();

            //将中节点加进数组
            result.push_back(node->val);
        }
    }
    return result;
}

//中序遍历
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    //定义迭代栈
    stack<TreeNode *> sta;

    //将根节点 push 进栈
    if (root != nullptr) sta.push(root);
    //遍历二叉树
    TreeNode *node;
    while (!sta.empty()) {
        //取得栈顶节点
        node = sta.top();
        if (node != nullptr) {
            //将当前元素弹出
            sta.pop();

            //将右节点 push 进队列
            if (node->right) sta.push(node->right);

            //添加中节点
            sta.push(node);
            //中节点处理过，添加一个处理过的标记
            sta.push(nullptr);

            //添加左节点
            if (node->left) sta.push(node->left);
        } else {    //遇到空节点时
            //将空节点弹出
            sta.pop();
            //取得中节点
            node = sta.top();
            sta.pop();

            //将中节点加进数组
            result.push_back(node->val);
        }
    }
    return result;
}

//后序遍历
vector<int> postorderTraversal(TreeNode *root) {

    vector<int> result;
    //定义迭代栈
    stack<TreeNode *> sta;

    //将根节点 push 进栈
    if (root != nullptr) sta.push(root);
    //遍历二叉树
    TreeNode *node;
    while (!sta.empty()) {
        //取得栈顶节点
        node = sta.top();
        if (node != nullptr) {
            //将当前元素弹出
            sta.pop();

            //添加中节点
            sta.push(node);
            //中节点处理过，添加一个处理过的标记
            sta.push(nullptr);

            //将右节点 push 进队列
            if (node->right) sta.push(node->right);

            //添加左节点
            if (node->left) sta.push(node->left);
        } else {    //遇到空节点时
            //将空节点弹出
            sta.pop();
            //取得中节点
            node = sta.top();
            sta.pop();

            //将中节点加进数组
            result.push_back(node->val);
        }
    }
    return result;
}

int main3() {
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