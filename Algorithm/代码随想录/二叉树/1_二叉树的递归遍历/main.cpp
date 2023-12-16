#include <iostream>
#include <vector>

/*
 * ʹ�õݹ�ʵ�ֶ�������ǰ���С����������
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

//ǰ���������
void preorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    result.push_back(node->val);
    preorder(node->left, result);
    preorder(node->right, result);
}

//�����������
void inorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    inorder(node->left, result);
    result.push_back(node->val);
    inorder(node->right, result);
}

//�����������
void postorder(TreeNode *node, vector<int> &result) {
    if (node == nullptr) return;

    postorder(node->left, result);
    postorder(node->right, result);
    result.push_back(node->val);
}

//ǰ�����
vector<int> preorderTraversal(TreeNode *root) {
    vector<int> result;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    preorder(root, result);
    return result;
}

//�������
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    inorder(root, result);
    return result;
}

//�������
vector<int> postorderTraversal(TreeNode *root) {
    vector<int> result;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
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

    cout << "ǰ�����: ";
    for (int item: preorderResult) {
        cout << item << " ";
    }
    cout << "\n�������: ";
    for (int item: inorderResult) {
        cout << item << " ";
    }
    cout << "\n�������: ";
    for (int item: postorderResult) {
        cout << item << " ";
    }
    cout << endl;
    return 0;
}