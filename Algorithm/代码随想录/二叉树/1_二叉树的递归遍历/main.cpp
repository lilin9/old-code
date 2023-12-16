#include <iostream>
#include <vector>

/*
 * 使用递归实现二叉树的前、中、后序遍历。
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

//前序遍历代码
void preorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    result.push_back(node->val);
    preorder(node->left, result);
    preorder(node->right, result);
}

//中序遍历代码
void inorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    inorder(node->left, result);
    result.push_back(node->val);
    inorder(node->right, result);
}

//后序遍历代码
void postorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    postorder(node->left, result);
    postorder(node->right, result);
    result.push_back(node->val);
}

//前序遍历
vector<int> preorderTraversal(TreeNode *root) {
    vector<int> result;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    preorder(root, result);
    return result;
}

//中序遍历
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    inorder(root, result);
    return result;
}

//后序遍历
vector<int> postorderTraversal(TreeNode *root) {
    vector<int> result;

    //判空
    if (root == nullptr) return result;
    //如果只有一个节点
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    postorder(root, result);
    return result;
}

int main1() {
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