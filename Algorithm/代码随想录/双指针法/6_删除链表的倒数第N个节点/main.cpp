#include <iostream>

/*
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *removeNthFromEnd(ListNode *head, int n) {
    //判空和单个节点判断
    if (head == nullptr || (head->next == nullptr && n == 1)) {
        return nullptr;
    }

    //虚拟头节点
    auto *virtualHead = new ListNode(-1, head);
    //定义快慢指针
    ListNode *quickPoint = virtualHead;
    ListNode *slowPoint = virtualHead;

    //第一次循环，让快指针移动 n 步
    for (; n > 0; --n) {
        quickPoint= quickPoint->next;
    }

    //第二次循环，让快指针指向链表最后一个节点
    //慢指针指向链表的倒数第 n 个节点的前一个节点
    while (quickPoint->next != nullptr) {
        quickPoint = quickPoint->next;
        slowPoint = slowPoint->next;
    }

    //删除链表的倒数第 n 个节点
    slowPoint->next = slowPoint->next->next;
    //返回头节点
    return virtualHead->next;
}

int main6() {
//    auto *head = new ListNode(1);
//    auto *node2 = new ListNode(2);
//    auto *node3 = new ListNode(3);
//    auto *node4 = new ListNode(4);
//    auto *node5 = new ListNode(5);
//
//    head->next = node2;
//    node2->next = node3;
//    node3->next = node4;
//    node4->next = node5;
//
//    int n = 2;  //1,2,3,5

    auto *head = new ListNode(1);
    auto *node2 = new ListNode(2);

    head->next = node2;

    int n = 1;  //1

    ListNode *newHead = removeNthFromEnd(head, n);

    ListNode *temp = newHead;
    while (temp != nullptr) {
        std::cout << temp->val << " ";
        temp = temp->next;
    }
    std::cout << std::endl;
    return 0;
}