#include <iostream>

/*
 * 题意：反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *reverseList(ListNode *head) {
    //判空和单个节点判断
    if (head == nullptr || head->next == nullptr) return head;

    //定义两个指针
    ListNode *leftPoint = nullptr;
    ListNode *rightPoint = head;
    //定义辅助节点
    ListNode *temp;

    //遍历修改链表顺序
    while (rightPoint != nullptr) {
        //记忆 rightPoint 指向的下一个节点
        temp = rightPoint->next;
        //调换 rightPoint 的 next 指针指向
        rightPoint->next = leftPoint;
        //移动 leftPoint 和 rightPoint 两个指针
        leftPoint = rightPoint;
        rightPoint = temp;
    }

    return rightPoint;
}

int main5() {
    ListNode head(1);
    ListNode node2(2);
    ListNode node3(3);
    ListNode node4(4);
    ListNode node5(5);

    head.next = &node2;
    node2.next = &node3;
    node3.next = &node4;
    node4.next = &node5;    //5->4->3->2->1

    head = *reverseList(&head);

    ListNode *temp = &head;
    while (temp != nullptr) {
        std::cout << temp->val << " ";
        temp = temp->next;
    }
    std::cout << std::endl;
    return 0;
}