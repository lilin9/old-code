#include <iostream>
#include "vector"

/*
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null。
 *
 * 两个链表在节点 c1 开始相交：
 * A: a1 -> a2 -> c1 -> c2 -> c3
 * B: b1 -> b2 -> b3 -> c1 -> c2 -> c3
 *
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 * 示例 1：
 * A: 4 -> 1 -> 8 -> 4 -> 5
 * B: 5 -> 0 -> 1 -> 8 -> 4 -> 5
 * 输入：intersectVal = 8, listA = [4, 1, 8, 4, 5], listB = [5, 0, 1, 8, 4, 5], skipA = 2, skipB = 3
 * 输出：Intersected at '8'
 * 解释：相交节点的值为 8 (注意，如果两个链表相交则不能为0).
 * 从各自的表头开始算起，链表 A 为 [4, 1, 8, 4, 5]，链表 B 为 [5, 0, 1, 8, 4, 5]。
 * 在 A 中，相交节点前有 2 个节点，在 B 中，相交节点前有 3 个节点。
 *
 * 示例 2：
 * A: 0 -> 9 -> 1 -> 2 -> 4
 * B: 3 -> 2 -> 4
 * 输入：intersectVal = 2, listA = [0, 9, 1, 2, 4], listB = [3, 2, 4], skipA = 3, skipB = 1
 * 输出：Intersected at '2'
 * 解释：相交节点的值为 2 (注意，如果两个链表相交则不能为0).
 * 从各自的表头开始算起，链表 A 为 [0, 9, 1, 2, 4]，链表 B 为 [3, 2, 4]。
 * 在 A 中，相交节点前有 3 个节点，在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 * A: 2 -> 6 -> 4
 * B: 1 -> 5
 * 输入：intersectVal = 0, listA = [2, 6, 4], listB = [1, 5], skipA = 3, skipB = 2
 * 输出：null
 * 解释：从各自的表头开始算起，链表 A 为 [2, 6, 4]，链表 B 为 [1, 5]。
 * 由于这两个链表不相交，所以 intersectVal，必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null。
 */

struct ListNode {
    int val;
    ListNode *next;

    ListNode(int x) : val(x), next(NULL) {}
};

//获取链表长度
int getListLength(ListNode *head) {
    //参数判空
    if (head == nullptr) {
        return 0;
    }

    ListNode* temp = head;
    //定义一个计数器
    int length = 0;
    while (temp != nullptr) {
        length++;
        temp = temp->next;
    }
    return length;
}

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
    //参数判空
    if (headA == nullptr || headB == nullptr) {
        return nullptr;
    }

    //临时指针
    ListNode* tempA = headA;
    ListNode* tempB = headB;

    //获取两个链表的长度
    int lenA = getListLength(headA);
    int lenB = getListLength(headB);

    //判断哪个链表更长
    int sub = 0;
    if (lenA-lenB < 0) {
        sub = lenB - lenA;
        while (sub--) {
            tempB = tempB->next;
        }
    } else {
        sub = lenA - lenB;
        while (sub--) {
            tempA = tempA->next;
        }
    }

    //从tempA和tempB当前指向的节点开始，遍历链表
    while (tempA != nullptr || tempB != nullptr) {
        if (tempA == tempB) {
            return tempA;
        }
        tempA = tempA->next;
        tempB = tempB->next;
    }

    return nullptr;
}

int main6() {
    auto* headA = new ListNode(4);
    auto* headB = new ListNode(5);

    auto* node1 = new ListNode(1);
    auto* node0 = new ListNode(0);
    auto* node1_ = new ListNode(1);
    auto* node8 = new ListNode(8);
    auto* node4 = new ListNode(4);
    auto* node5 = new ListNode(5);

    headA->next = node1;
    node1->next = node8;
    node8->next = node4;
    node4->next = node5;
    headB->next = node0;
    node0->next = node1_;
    node1_->next = node8;

    std::cout << "Intersected at " << getIntersectionNode(headA, headB)->val << std::endl;  //8
    return 0;
}










