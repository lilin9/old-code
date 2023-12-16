#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

/*
 * ʹ�õ���ʵ�ֶ�������ǰ���С����������
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

//ǰ�����
vector<int> preorderTraversal(TreeNode *root) {
    //��Ž��
    vector<int> result;
    //�������ջ
    stack<TreeNode*> sta;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //����������������ջΪ��ʱ�˳�
    TreeNode *node;
    sta.push(root);
    while (!sta.empty()) {
        //ȡ��ջ�ڽڵ㣬�����䵯��
        node = sta.top();
        sta.pop();
        //����ֵ���� result ����
        result.push_back(node->val);

        //�ڽڵ�ֵ��Ϊ�յ�����£����������ӽڵ���ջ
        if (node->right) sta.push(node->right);
        if (node->left) sta.push(node->left);
    }
    return result;
}

//�������
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    //�������ջ
    stack<TreeNode*> sta;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //����������������ջΪ��ʱ�˳�
    TreeNode *node = root;
    //����������
    while (node != nullptr || !sta.empty()) {
        //һֱ������Ҷ�ӽڵ�
        if (node != nullptr) {
            //���ڵ���ջ
            sta.push(node);
            //��ȡ��ڵ�
            node = node->left;
        } else {
            //ȡ��ջ���ڵ�
            node = sta.top();
            sta.pop();
            //���ڵ�ֵ��������
            result.push_back(node->val);
            //��ȡ�ҽڵ�
            node = node->right;
        }
    }
    return result;
}

//�������
vector<int> postorderTraversal(TreeNode *root) {
    vector<int> result;
    //�������ջ
    stack<TreeNode*> sta;

    //�п�
    if (root == nullptr) return result;
    //���ֻ��һ���ڵ�
    if (root->left == nullptr && root->right == nullptr) {
        result.push_back(root->val);
        return result;
    }

    //����������������ջΪ��ʱ�˳�
    TreeNode *node;
    sta.push(root);
    while (!sta.empty()) {
        //ȡ��ջ�ڽڵ㣬�����䵯��
        node = sta.top();
        sta.pop();
        //����ֵ���� result ����
        result.push_back(node->val);

        //�ڽڵ�ֵ��Ϊ�յ�����£����������ӽڵ���ջ
        if (node->left) sta.push(node->left);
        if (node->right) sta.push(node->right);
    }
    //��ת����
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