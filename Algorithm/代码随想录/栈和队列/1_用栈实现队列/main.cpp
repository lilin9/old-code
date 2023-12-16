#include <iostream>
#include <stack>

/*
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop()   -- 从队列首部移除元素。
 * peek()  -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * 示例:
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明:
 * - 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size,
 * 和 is empty 操作是合法的。
 * - 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，
 * 只要是标准的栈操作即可。
 * - 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 */

class MyQueue {
private:
    std::stack<int> *values;
    std::stack<int> *temp;
public:
    MyQueue() {
        this->values = new std::stack<int>();
        this->temp = new std::stack<int>();
    }

    //将一个元素放入队列的尾部
    void push(int x) {
        this->values->push(x);
    }

    //从队列首部移除元素
    int pop() {
        //将 values 中的值放入辅助栈
        int val;
        while (!this->values->empty()) {
            //获取栈顶元素
            val = this->values->top();
            //将栈顶元素弹出
            this->values->pop();
            //将 val 放入辅助栈
            this->temp->push(val);
        }

        //将辅助栈的栈顶元素获取并弹出，即为返回值
        int result = this->temp->top();
        this->temp->pop();

        //在将辅助栈的值重新放入 values
        while (!this->temp->empty()) {
            val = this->temp->top();
            this->temp->pop();
            this->values->push(val);
        }

        //返回
        return result;
    }

    //返回队列首部的元素
    int peek() {
        //将 values 中的值放入辅助栈
        int val;
        while (!this->values->empty()) {
            //获取栈顶元素
            val = this->values->top();
            //将栈顶元素弹出
            this->values->pop();
            //将 val 放入辅助栈
            this->temp->push(val);
        }

        //将辅助栈的栈顶元素获取并弹出，即为返回值
        int result = this->temp->top();

        //在将辅助栈的值重新放入 values
        while (!this->temp->empty()) {
            val = this->temp->top();
            this->temp->pop();
            this->values->push(val);
        }

        //返回
        return result;
    }

    //返回队列是否为空
    bool empty() {
        return this->values->empty();
    }
};

int main1() {
    MyQueue queue;
    queue.push(1);
    queue.push(2);

    std::cout << queue.peek() << std::endl;  // 返回 1
    std::cout << queue.pop() << std::endl;  // 返回 1
    std::cout << queue.empty() << std::endl;  // 返回 false
    return 0;
}