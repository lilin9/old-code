#include <iostream>
#include "vector"

/*
 * 题意：删除链表中等于给定值 val 的所有节点。
 *
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}
};

ListNode *removeElements(ListNode *head, int val) {
    //判断空指针
    if (head == nullptr) {
        return nullptr;
    }

    auto *dummyHead = new ListNode();
    dummyHead->next = head;

    ListNode* temp = dummyHead;
    while (temp->next != nullptr) {
        if (temp->next->val == val) {
            ListNode *tem = temp->next;
            temp->next = temp->next->next;
            delete tem;
        } else {
            temp = temp->next;
        }

    }
    head = dummyHead->next;
    delete dummyHead;
    return head;
}

ListNode *addElements(const std::vector<int> &values) {
    auto *head = new ListNode(values[0]);

    ListNode *item = head;
    for (int i = 1; i < values.size(); ++i) {
        auto *node = new ListNode(values[i]);
        item->next = node;
        item = item->next;
    }
    return head;
}

void printListNode(ListNode *head) {
    ListNode *temp = head;

    while (temp != nullptr) {
        std::cout << temp->val << " ";
        temp = temp->next;
    }
    std::cout << std::endl;
}

int main1() {
    ListNode *head = addElements({1, 2, 6, 3, 4, 5, 6});
    int val = 6;    //1,2,3,4,5
//    ListNode *head = addElements({1,2,2,1});
//    int val = 2;    //1,1
//    ListNode *head = addElements({7, 7, 7, 7});
//    int val = 7;    //[]

    printListNode(head);
    head = removeElements(head, val);
    printListNode(head);
    delete head;
    return 0;
}