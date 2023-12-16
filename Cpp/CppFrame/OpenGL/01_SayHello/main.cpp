#include "glad/glad.h"
#include "GLFW/glfw3.h"
#include <iostream>

//当用户改变窗口的大小时，动态改变视口view的大小
void frameBufferSizeCallback(GLFWwindow* window, int width, int height){
    glViewport(0, 0, width, height);
}

//检测用户是否按下回车键（Esc），按下就关闭窗口
void processInput(GLFWwindow* window) {
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
        glfwSetWindowShouldClose(window, true);
    }
}

int main() {
    //初始化GLFW
    glfwInit();
    //告诉glfw，使用的opengl主版本号（Major）为3
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    //告诉glfw，使用的opengl次版本号（Minor）为3
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    //使用核心模式
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


    //创建窗口对象，并设置初始值
    GLFWwindow* window = glfwCreateWindow(800, 600, "LearnOpenGL", NULL, NULL);
    if (window == NULL) {
        std::cout << "Failed to create GLFW window" << std::endl;
        glfwTerminate();
        return -1;
    }
    //通知GLFW将我们窗口的上下文设置为当前线程的主上下文
    glfwMakeContextCurrent(window);

    //初始化glad
    if (!gladLoadGLLoader((GLADloadproc) glfwGetProcAddress)) {
        std::cout << "Failed to initialize GLAD" << std::endl;
        return -1;
    }

    //设置窗口渲染窗口的宽高
    //glViewport函数前两个参数控制窗口左下角的位置。第三个和第四个参数控制渲染窗口的宽度和高度（像素）。
    glViewport(0, 0, 800, 600);

    //注册frameBufferSizeCallback这个回调函数，告诉GLFW我们希望每当窗口调整大小的时候调用这个函数
    glfwSetFramebufferSizeCallback(window, frameBufferSizeCallback);

    //添加渲染循环，让引擎持续渲染窗口
    while (!glfwWindowShouldClose(window)) {
        //检测用户是否按下了键盘的回车键，是就关闭窗口
        processInput(window);


        //glClearColor函数是一个状态设置函数
        //当颜色缓冲被清除后，整个颜色缓冲都会被填充为glClearColor里所设置的颜色
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
        //glClear函数则是一个状态使用的函数
        //glClear函数 + GL_COLOR_BUFFER_BIT缓冲目标 可以用来清空屏幕的颜色缓冲
        glClear(GL_COLOR_BUFFER_BIT);


        //检查有没有触发什么事件（比如键盘输入、鼠标移动等）、更新窗口状态，
        //并调用对应的回调函数（可以通过回调方法手动设置）
        glfwSwapBuffers(window);
        //绘制颜色，并输出显示在屏幕上
        glfwPollEvents();
    }

    //释放、删除之前的分配的所有资源
    glfwTerminate();
    return 0;
}






