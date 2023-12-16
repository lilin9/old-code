#include <iostream>
#include <queue>
#include <vector>

/*
 * 使用队列实现栈的下列操作：
 * - push(x) -- 元素 x 入栈
 * - pop()   -- 移除栈顶元素
 * - top()   -- 获取栈顶元素
 * - empty() -- 返回栈是否为空
 *
 * 注意:
 * - 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size,
 * 和 is empty 这些操作是合法的。
 * - 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 ,
 * 只要是标准的队列操作即可。
 * - 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 * 示例：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 */

class MyStack {
private:
    std::queue<int> *values;
public:
    MyStack() {
        this->values = new std::queue<int>();
    }

    //元素 x 入栈
    void push(int x) {
        this->values->push(x);
    }

    //移除栈顶元素
    int pop() {
        if (this->values->empty()) return 0;

        //获取队列的大小
        int size = this->values->size() - 1;
        //循环弹出队列元素
        while (size--) {
            this->values->push(this->values->front());
            this->values->pop();
        }

        //弹出此时的队首元素
        int result = this->values->front();
        this->values->pop();

        //返回队首元素
        return result;
    }

    //获取栈顶元素
    int top() {
        return this->values->back();
    }

    //返回栈是否为空
    bool empty() {
        return this->values->size() == 0;
    }
};

int main2() {
//    auto *myStack = new MyStack();
//    myStack->push(1);
//    myStack->push(2);
//    std::cout << myStack->top() << std::endl; // 返回 2
//    std::cout << myStack->pop() << std::endl; // 返回 2
//    std::cout << myStack->pop() << std::endl; // 返回 1
//    std::string empty = myStack->empty() ? "true" : "false";
//    std::cout << empty << std::endl; // 返回 true

    auto *myStack = new MyStack();
    myStack->push(1);
    myStack->push(2);
    std::cout << myStack->top() << std::endl; // 返回 2
    std::cout << myStack->pop() << std::endl; // 返回 2
    std::string empty = myStack->empty() ? "true" : "false";
    std::cout << empty << std::endl; // 返回 False

    return 0;
}