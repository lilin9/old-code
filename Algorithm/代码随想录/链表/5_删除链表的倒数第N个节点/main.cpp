#include <iostream>
#include "vector"

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
    //参数判空
    if (head == nullptr) {
        return nullptr;
    }

    //头节点单个节点判断
    if (head->next == nullptr) {
        return n == 1 ? nullptr : head;
    }

    //定义虚拟头节点，指向头节点
    auto *virtualNode = new ListNode(-1, head);
    //定义快慢指针
    ListNode *quickPoint = head;
    ListNode *slowPoint = virtualNode;
    //定义一个定时器
    int count = 0;

    //循环遍历
    while (quickPoint != nullptr) {
        if (count != n) {
            //定时器计数
            ++count;
            //只移动快指针
            quickPoint = quickPoint->next;
            continue;
        }
        //计数器不再计数，快慢指针一起移动
        quickPoint = quickPoint->next;
        slowPoint = slowPoint->next;
    }

    //循环结束后，慢指针指向的下一个节点就是要删除的节点
    slowPoint->next = slowPoint->next->next;

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

int main5() {
    ListNode *head = addElements({1, 2});
    printListNode(head);

    head = removeNthFromEnd(head, 1);   //1
    printListNode(head);

//    ListNode* head = addElements({1, 2, 3, 4, 5});
//    printListNode(head);
//
//    head = removeNthFromEnd(head, 2);   //1,2,3,5
//    printListNode(head);
    return 0;
}