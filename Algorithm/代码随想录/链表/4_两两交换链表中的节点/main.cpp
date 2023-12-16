#include <iostream>
#include "vector"

/*
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例1：
 * 1 -> 2 -> 3 -> 4
 * 2 -> 1 -> 4 -> 3
 *
 * 示例2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例3：
 * 输入：head = [1]
 * 输出：[1]
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *swapPairs(ListNode *head) {
    //判空
    if (head == nullptr) {
        return nullptr;
    }
    //单个头节点判断
    if (head->next == nullptr) {
        return head;
    }

    //定义虚拟头节点，记录头结点的位置
    auto *virtualNode = new ListNode(-1, head);
    //定义两个快慢指针
    ListNode *quickPoint = virtualNode->next;
    ListNode *slowPoint = virtualNode;

    //循环
    while (slowPoint->next != nullptr && quickPoint != nullptr) {
        //快慢指针后移
        slowPoint = slowPoint->next;
        quickPoint = slowPoint->next;

        //交换快慢指针指向的节点位置
        slowPoint->next = quickPoint->next;
        quickPoint->next = slowPoint;
    }

    return virtualNode->next;
}

ListNode *addElements(std::vector<int> values) {
    auto *head = new ListNode(values[0]);

    ListNode *temp = head;
    for (int i = 1; i < values.size(); ++i) {
        auto *node = new ListNode(values[i]);
        temp->next = node;
        temp = temp->next;
    }

    return head;
}

void printListNode(ListNode *head) {
    if (head == nullptr) {
        return;
    }

    ListNode *temp = head;
    while (temp != nullptr) {
        std::cout << temp->val << " ";
        temp = temp->next;
    }
    std::cout << std::endl;
}

int main4() {
//    ListNode* head = addElements({1, 2, 3, 4});
//    printListNode(head);
//
//    head = swapPairs(head);
//    printListNode(head);

    std::cout << __cplusplus << std::endl;
    return 0;
}