#include <iostream>

/*
 * 在链表类中实现这些功能：
 * 1.get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * 2.addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个
 * 节点。
 * 3.addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * 4.addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，
 * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * 5.deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 */

class MyLinkedList {
public:
    struct LinkedList {
        int val;
        LinkedList *next;

        LinkedList(int val) : val(val), next(nullptr) {}
    };

    MyLinkedList() {
        //初始化虚拟头节点
        this->virtualHead = new LinkedList(-1);
    }

    //获取链表中第 index 个节点的值。如果索引无效，则返回-1。
    int get(int index) {
        //参数判断
        if (index < 0) {
            return -1;
        }

        //定义一个计数器
        int count = 0;
        //要返回的值
        int result = 0;
        //遍历链表
        LinkedList *temp = this->virtualHead;
        while (temp != nullptr) {
            //判断计数器和index是否相等
            if (index == count) {
                result = temp->next->val;
                break;
            }
            //否则，计数器加1，temp节点往后遍历
            count++;
            temp = temp->next;
        }

        return result;
    }

    //在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
    void addAtHead(int val) {
        //创建新节点
        auto *newHead = new LinkedList(val);
        //新建节点指向头节点
        newHead->next = this->virtualHead->next;
        //虚拟节点继续指向新头节点
        this->virtualHead->next = newHead;
        //长度加1
        this->length++;
    }

    //将值为 val 的节点追加到链表的最后一个元素
    void addAtTail(int val) {
        //创建新节点
        auto *tailNode = new LinkedList(val);
        //辅助节点
        LinkedList *temp = this->virtualHead;
        //判断链表有没有头节点
        if (this->virtualHead->next == nullptr && this->virtualHead->val == -1) {
            //让新节点直接成为头节点
            this->virtualHead->next = tailNode;
            //结束程序
            return;
        }
        //遍历
        while (temp != nullptr) {
            //判断是不是尾节点
            if (temp->next == nullptr) {
                //添加新尾节点
                temp->next = tailNode;
                //长度加1
                this->length++;
                break;
            }
            temp = temp->next;
        }
    }

    //在链表中的第 index 个节点之前添加值为 val 的节点。如果 index 等于链表的长度，
    //则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，
    //则在头部插入节点。
    void addAtIndex(int index, int val) {
        //index 大于链表长度，不会插入节点
        if (index > this->length-1) {
            return;
        }

        //index 小于0，在头部插入节点
        if (index <= 0) {
            this->addAtHead(val);
            return;
        }

        //index 等于链表长度，在链表末尾插入节点
        if (index == this->length - 1) {
            this->addAtTail(val);
            return;
        }

        //定义一个计数器
        int count = 0;
        //辅助节点
        LinkedList* temp = this->virtualHead;
        //创建新节点
        auto* newNode = new LinkedList(val);
        //循环
        while (temp != nullptr) {
            if (count == index) {
                //将新节点加入链表
                newNode->next = temp->next;
                temp->next = newNode;
                //结束循环
                break;
            }
            //计数器加1
            count++;
            //temp 后移
            temp = temp->next;
        }
    }

    //如果索引 index 有效，则删除链表中的第 index 个节点。
    void deleteAtIndex(int index) {
        //判断 index 有效
        if (index < 0 || index > this->length-1) {
            return;
        }

        //定时器
        int count = 0;
        //辅助节点
        LinkedList* temp = this->virtualHead;
        //遍历
        while (temp != nullptr) {
            if (count == index) {
                LinkedList* deleteNode = temp->next;
                temp->next = temp->next->next;
                delete deleteNode;
                break;
            }
            //计时器加1
            count++;
            //temp 后移
            temp = temp->next;
        }
    }

    //打印链表
    void printLinkedList() {
        LinkedList* temp = this->virtualHead->next;
        while (temp != nullptr) {
            std::cout << temp->val << " ";
            temp = temp->next;
        }
        std::cout << std::endl;
    }

private:
    //定义虚拟头节点，next指针永远指向头节点
    LinkedList *virtualHead;
    //链表长度
    int length;
};

int main2() {
//    auto* linkedList = new MyLinkedList();
//    linkedList->addAtHead(1);
//    linkedList->addAtTail(3);   //1, 3
//    linkedList->printLinkedList();
//
//    linkedList->addAtIndex(1, 2);   //1, 2, 3
//    linkedList->printLinkedList();
//
//    std::cout << linkedList->get(1) << std::endl;   //2
//
//    linkedList->deleteAtIndex(1);   //1, 3
//    linkedList->printLinkedList();


    auto* linkedList = new MyLinkedList();
    linkedList->addAtHead(1);   //1
    linkedList->printLinkedList();

    linkedList->addAtTail(3);   //1, 3
    linkedList->printLinkedList();

    linkedList->addAtIndex(1, 2);   //1, 2, 3
    linkedList->printLinkedList();

    std::cout << linkedList->get(1) << std::endl; //2

    linkedList->deleteAtIndex(1);   //1, 3
    linkedList->printLinkedList();

    std::cout << linkedList->get(1) << std::endl;    //3
    return 0;
}