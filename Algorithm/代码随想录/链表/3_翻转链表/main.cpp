#include <iostream>
#include "vector"

/*
 * 反转一个单链表。
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

ListNode* reverseList(ListNode* head) {
    if (head == nullptr) {
        return nullptr;
    }

    //定义快慢指针
    ListNode* quickPoint = head;
    ListNode* slowPoint = nullptr;
    //定义一个寄存节点
    auto* tempNode = new ListNode(-1, nullptr);

    //遍历链表
    while (quickPoint != nullptr) {
        //临时节点寄存快指针要指向的下一个节点
        tempNode->next = quickPoint->next;
        //快指针指向的节点指向慢指针指向的节点
        quickPoint->next = slowPoint;
        //快慢指针后移
        //一定要先将慢指针后移，再移动快指针
        slowPoint = quickPoint;
        quickPoint = tempNode->next;
    }

    //释放寄存节点的空间
    delete tempNode;
    return slowPoint;
}

ListNode* addElements(std::vector<int> values) {
    auto* head = new ListNode(values[0]);

    ListNode* temp = head;
    for (int i = 1; i < values.size(); ++i) {
        auto* node = new ListNode(values[i]);
        temp->next = node;
        temp = temp->next;
    }

    return head;
}

void printListNode(ListNode* head) {
    if (head == nullptr) {
        return;
    }

    ListNode* temp = head;
    while (temp != nullptr) {
        std::cout << temp->val << " ";
        temp = temp->next;
    }
    std::cout << std::endl;
}

int main3() {
    ListNode* head = addElements({1, 2, 3, 4, 5});
    printListNode(head);

    head = reverseList(head);
    printListNode(head);
    return 0;
}