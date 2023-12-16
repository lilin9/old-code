#include <iostream>

/*
 * ����һ������ɾ������ĵ����� n ����㣬���ҷ��������ͷ��㡣
 * ���ף����ܳ���ʹ��һ��ɨ��ʵ����
 *
 * ʾ�� 1��
 * ���룺head = [1,2,3,4,5], n = 2
 * �����[1,2,3,5]
 *
 * ʾ�� 2��
 * ���룺head = [1], n = 1
 * �����[]
 *
 * ʾ�� 3��
 * ���룺head = [1,2], n = 1
 * �����[1]
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode() : val(0), next(nullptr) {}

    ListNode(int x) : val(x), next(nullptr) {}

    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

ListNode *removeNthFromEnd(ListNode *head, int n) {
    //�пպ͵����ڵ��ж�
    if (head == nullptr || (head->next == nullptr && n == 1)) {
        return nullptr;
    }

    //����ͷ�ڵ�
    auto *virtualHead = new ListNode(-1, head);
    //�������ָ��
    ListNode *quickPoint = virtualHead;
    ListNode *slowPoint = virtualHead;

    //��һ��ѭ�����ÿ�ָ���ƶ� n ��
    for (; n > 0; --n) {
        quickPoint= quickPoint->next;
    }

    //�ڶ���ѭ�����ÿ�ָ��ָ���������һ���ڵ�
    //��ָ��ָ������ĵ����� n ���ڵ��ǰһ���ڵ�
    while (quickPoint->next != nullptr) {
        quickPoint = quickPoint->next;
        slowPoint = slowPoint->next;
    }

    //ɾ������ĵ����� n ���ڵ�
    slowPoint->next = slowPoint->next->next;
    //����ͷ�ڵ�
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