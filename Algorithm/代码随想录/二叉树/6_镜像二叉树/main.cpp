#include <iostream>

/*
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如：二叉树 [1, 2, 2, 3, 4, 4, 3] 是对称的。
 * 但是二叉树 [1, 2, 2, null, 3, null, 3] 则不是镜像对称的。
 */

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;

    TreeNode() : val(0), left(nullptr), right(nullptr) {}

    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}

    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

bool compare(TreeNode *left, TreeNode *right) {
    //左节点为空，右节点不为空
    if (left == nullptr && right != nullptr) return false;
    //左节点不为空，右节点为空
    else if (left != nullptr && right == nullptr) return false;
    //左右节点都为空
    else if (left == nullptr && right == nullptr) return true;
    //左右节点都不为空，比较数值
    else if (left->val != right->val) return false;

    //比较二叉树外侧是否对称
    bool outside = compare(left->left, right->right);
    //比较二叉树内侧是否对称
    bool inside = compare(left->right, right->left);
    //判断是否左右子树对称
    return outside && inside;
}

bool isSymmetric(TreeNode *root) {
    if (root == nullptr) return false;

    return compare(root->left, root->right);
}

int main() {
    TreeNode *root = new TreeNode(1);
    TreeNode *node2 = new TreeNode(2);
    TreeNode *node3 = new TreeNode(3);
    TreeNode *node2_ = new TreeNode(2);
    TreeNode *node3_ = new TreeNode(3);

    root->left = node2;
    root->right = node2_;
    node2->right = node3;
    node2_->right = node3_; //false

    // TreeNode *root = new TreeNode(1);
    // TreeNode *node2 = new TreeNode(2);
    // TreeNode *node3 = new TreeNode(3);
    // TreeNode *node4 = new TreeNode(4);
    // TreeNode *node2_ = new TreeNode(2);
    // TreeNode *node3_ = new TreeNode(3);
    // TreeNode *node4_ = new TreeNode(4);

    // root->left = node2;
    // root->right = node2_;
    // node2->left = node3;
    // node2->right = node4;
    // node2_->left = node4_;
    // node2_->right = node3_;   //true


    if (isSymmetric(root)) {
        std::cout << "true" << std::endl;
    } else {
        std::cout << "false" << std::endl;
    }
    return 0;
}