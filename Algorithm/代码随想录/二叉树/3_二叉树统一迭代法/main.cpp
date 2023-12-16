#include <iostream>
#include <vector>
#include <stack>

/*
 * ͳһ�������ж�����ǰ�к�������ķ��
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
    vector<int> result;
    //�������ջ
    stack<TreeNode *> sta;

    //�����ڵ� push ��ջ
    if (root != nullptr) sta.push(root);
    //����������
    TreeNode *node;
    while (!sta.empty()) {
        //ȡ��ջ���ڵ�
        node = sta.top();
        if (node != nullptr) {
            //����ǰԪ�ص���
            sta.pop();

            //���ҽڵ� push ������
            if (node->right) sta.push(node->right);

            //�����ڵ�
            if (node->left) sta.push(node->left);

            //����нڵ�
            sta.push(node);
            //�нڵ㴦��������һ��������ı��
            sta.push(nullptr);
        } else {    //�����սڵ�ʱ
            //���սڵ㵯��
            sta.pop();
            //ȡ���нڵ�
            node = sta.top();
            sta.pop();

            //���нڵ�ӽ�����
            result.push_back(node->val);
        }
    }
    return result;
}

//�������
vector<int> inorderTraversal(TreeNode *root) {
    vector<int> result;
    //�������ջ
    stack<TreeNode *> sta;

    //�����ڵ� push ��ջ
    if (root != nullptr) sta.push(root);
    //����������
    TreeNode *node;
    while (!sta.empty()) {
        //ȡ��ջ���ڵ�
        node = sta.top();
        if (node != nullptr) {
            //����ǰԪ�ص���
            sta.pop();

            //���ҽڵ� push ������
            if (node->right) sta.push(node->right);

            //����нڵ�
            sta.push(node);
            //�нڵ㴦��������һ��������ı��
            sta.push(nullptr);

            //�����ڵ�
            if (node->left) sta.push(node->left);
        } else {    //�����սڵ�ʱ
            //���սڵ㵯��
            sta.pop();
            //ȡ���нڵ�
            node = sta.top();
            sta.pop();

            //���нڵ�ӽ�����
            result.push_back(node->val);
        }
    }
    return result;
}

//�������
vector<int> postorderTraversal(TreeNode *root) {

    vector<int> result;
    //�������ջ
    stack<TreeNode *> sta;

    //�����ڵ� push ��ջ
    if (root != nullptr) sta.push(root);
    //����������
    TreeNode *node;
    while (!sta.empty()) {
        //ȡ��ջ���ڵ�
        node = sta.top();
        if (node != nullptr) {
            //����ǰԪ�ص���
            sta.pop();

            //����нڵ�
            sta.push(node);
            //�нڵ㴦��������һ��������ı��
            sta.push(nullptr);

            //���ҽڵ� push ������
            if (node->right) sta.push(node->right);

            //�����ڵ�
            if (node->left) sta.push(node->left);
        } else {    //�����սڵ�ʱ
            //���սڵ㵯��
            sta.pop();
            //ȡ���нڵ�
            node = sta.top();
            sta.pop();

            //���нڵ�ӽ�����
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