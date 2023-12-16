#include <iostream>
#include "vector"

/*
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 * 示例1：
 * 输入：head=[3, 2, 0, -4]，pos=1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点
 *
 * 示例2：
 * 输入：head=[1, 2]，pos=0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode(int x) : val(x), next(NULL) {}
};

ListNode *detectCycle(ListNode *head) {
    if (head == nullptr || head->next == nullptr) {
        return nullptr;
    }

    //定义快慢指针
    ListNode* quickPoint = head;
    ListNode* slowPoint = head;

    //如果快指针下一个节点是null，说明链表无环
    while (quickPoint != nullptr && quickPoint->next != nullptr) {
        //快慢指针同时前进，快指针一次走两个节点，慢指针一次走一个节点
        quickPoint = quickPoint->next->next;
        slowPoint = slowPoint->next;

        //当快慢指针相遇时
        if (quickPoint == slowPoint) {
            //让慢指针继续指向当前节点，快指针重新指向头节点
            quickPoint = head;
            //第二次循环，当这次循环快慢指针相遇时，
            //其相遇的节点就是环的入口节点
            while (quickPoint != slowPoint) {
                //第二次循环改为快慢指针同时一次走一个节点
                slowPoint = slowPoint->next;
                quickPoint = quickPoint->next;
            }
            return slowPoint;
        }
    }
    return nullptr;
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

int main7() {
    ListNode* head = addElements({1, 2});
    head->next->next = head;  //1
//    ListNode* head = addElements({3, 2, 0, -4});
//    head->next->next->next->next = head->next;  //2

    std::cout << detectCycle(head)->val << std::endl;
    return 0;
}