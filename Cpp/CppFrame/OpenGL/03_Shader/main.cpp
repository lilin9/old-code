#include "glad/glad.h"
#include "GLFW/glfw3.h"
#include <iostream>
#include <cmath>

//定义顶点位置数据
const float vertices[] = {
        //位置值                               //颜色值
        -0.5f, -0.5f, 0.0f,     1.0f, 0.0f, 0.0f,
        0.5f, -0.5f, 0.0f,      0.0f, 1.0f, 0.0f,
        0.0f, 0.5f, 0.0f,    0.0f, 0.0f, 1.0f
};
//const float vertices[] = {
//        -0.5f, -0.5f, 0.0f,
//        0.5f, -0.5f, 0.0f,
//        0.0f, 0.5f, 0.0f
//};

//提供顶点着色器的编译源代码
const char *vertexShaderSource = "#version 330 core\n"  //修改三角形三个角为不同的颜色
                                 //位置变量的属性位置为 0
                                 "layout (location = 0) in vec3 aPos;\n"
                                 //颜色变量的属性位置值为 1
                                 "layout (location = 1) in vec3 aColor;\n"
                                 "\n"
                                 //向片段着色器输出一个颜色
                                 "out vec3 ourColor;\n"
                                 "\n"
                                 "void main()\n"
                                 "{\n"
                                 "   gl_Position = vec4(aPos, 1.0);\n"
                                    //将 ourColor 设置为从顶点数据数组 vertices 中得到的颜色值
                                 "   ourColor = aColor;\n"
                                 "}\0";
//const char *vertexShaderSource = "#version 330 core\n"  //随时间改变颜色代码
//                                 //位置变量的属性位置为0
//                                 "layout (location = 0) in vec3 aPos;\n"
//                                 "void main()\n"
//                                 "{\n"
//                                 "   gl_Position = vec4(aPos, 1.0);\n"
//                                 "}\0";
//const char *vertexShaderSource = "#version 330 core\n"    //原版代码
//                                 //位置变量的属性位置为0
//                                 "layout (location = 0) in vec3 aPos;\n"
//                                 //为判断着色器指定一个颜色输出
//                                 "out vec4 vertexColor;\n"
//                                 "void main()\n"
//                                 "{\n"
//                                 "   gl_Position = vec4(aPos, 1.0);\n"
//                                     //为输出变量设置暗红色的rgb数据
//                                 "   vertexColor = vec4(0.5, 0.0, 0.0, 1.0);\n"
//                                 "}\0";

//提供片段着色器的编译源代码
const char *fragmentShaderSource = "#version 330 core\n"  //修改三角形三个角为不同的颜色
                                   "out vec4 FragColor;\n"
                                   "in vec3 ourColor;\n"
                                   "\n"
                                   "void main()\n"
                                   "{\n"
                                   "    FragColor = vec4(ourColor, 1.0);\n"
                                   "}\0";
//const char *fragmentShaderSource = "#version 330 core\n"  //随时间改变颜色代码
//                                   "out vec4 FragColor;\n"
//                                   //在OpenGL程序代码中设定这个变量
//                                   "uniform vec4 ourColor;\n"
//                                   "void main()\n"
//                                   "{\n"
//                                   "    FragColor = ourColor;\n"
//                                   "}\0";
//const char *fragmentShaderSource = "#version 330 core\n"    //原版代码
//                                   "out vec4 FragColor;\n"
//                                   //接收从顶点着色器传来的输入变量（名称相同、类型相同）
//                                   "in vec4 vertexColor;\n"
//                                   "\n"
//                                   "void main()\n"
//                                   "{\n"
//                                   "    FragColor = vertexColor;\n"
//                                   "}\0";

//当用户改变窗口的大小时，动态改变视口view的大小
void frameBufferSizeCallback(GLFWwindow *window, int width, int height) {
    glViewport(0, 0, width, height);
}

//检测用户是否按下回车键（Esc），按下就关闭窗口
void processInput(GLFWwindow *window) {
    if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
        glfwSetWindowShouldClose(window, true);
    }
}

//判断在编译着色器源码过程中是否有错误发生
void compileShaderSourceCheck(const unsigned int &vertexShader) {
    int success;
    char infoLog[512];
    //检测着色器是否编译成功
    glGetShaderiv(vertexShader, GL_COMPILE_STATUS, &success);
    if (!success) {
        //获取错误日志
        glGetShaderInfoLog(vertexShader, 512, nullptr, infoLog);
        //打印错误日志
        std::cout << "ERROR::SHADER::VERTEX::COMPILATION_FAILED\n" << infoLog << std::endl;
    }
}

//判断在链接着色器时是否有错误出现
void linkedShaderProgramCheck(const unsigned int &shaderProgram) {
    int success;
    char infoLog[512];

    //检测着色器是否链接成功
    glGetProgramiv(shaderProgram, GL_LINK_STATUS, &success);
    if (!success) {
        //获取错误日志
        glGetProgramInfoLog(shaderProgram, 512, nullptr, infoLog);
        //打印错误日志
        std::cout << "ERROR::SHADER::PROGRAM::LINKED_FAILED\n" << infoLog << std::endl;
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
    GLFWwindow *window = glfwCreateWindow(800, 600, "LearnOpenGL", nullptr, nullptr);
    if (window == nullptr) {
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



    //创建顶点着色器
    unsigned int vertexShader;
    vertexShader = glCreateShader(GL_VERTEX_SHADER);
    //加载着色器源码并编译
    //第一个参数 着色器对象，第二个参数 传递的源码字符串个数，第三个参数 着色器源码，第四个参数 ?
    glShaderSource(vertexShader, 1, &vertexShaderSource, nullptr);
    glCompileShader(vertexShader);
    //判断着色器编译是否成功
    compileShaderSourceCheck(vertexShader);

    //创建片段着色器
    unsigned int fragmentShader;
    fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
    //加载片段着色器源码并且编译
    glShaderSource(fragmentShader, 1, &fragmentShaderSource, nullptr);
    glCompileShader(fragmentShader);
    //判断着色器编译是否成功
    compileShaderSourceCheck(fragmentShader);


    //将片段着色器和顶点着色器链接成一个着色器程序对象
    //创建着色器程序对象
    unsigned int shaderProgram;
    shaderProgram = glCreateProgram();
    //链接片段着色器和顶点着色器
    glAttachShader(shaderProgram, vertexShader);
    glAttachShader(shaderProgram, fragmentShader);
    glLinkProgram(shaderProgram);
    //判断着色器是否链接成功
    linkedShaderProgramCheck(shaderProgram);
    //调用着色器程序对象
    glUseProgram(shaderProgram);


    //创建 VBO 顶点缓冲对象，并绑定 id
    //创建 VAO 顶点数组对象，并绑定 id
    unsigned int VBO, VAO;
    glGenBuffers(1, &VBO);
    glGenVertexArrays(1, &VAO);
    //绑定 VAO 对象
    glBindVertexArray(VAO);
    //加载顶点数据到内存中
    glBindBuffer(GL_ARRAY_BUFFER, VBO);
    //第一个参数 目标缓冲的类型，第二个参数 传输数据的大小，第三个参数 要传输的数据，第四个参数 内存中数据的管理方式
    glBufferData(GL_ARRAY_BUFFER, sizeof(vertices), vertices, GL_STATIC_DRAW);


    //链接顶点属性
    //第一个参数 设置顶点属性位置指；
    //第二个参数 指定顶点属性的大小，顶点属性是一个vec3，它由3个值组成，所以大小是3。
    //第三个参数 指定数据的类型，这里是GL_FLOAT(GLSL中vec*都是由浮点数值组成的)。
    //第四个参数 是否希望数据被标准化。如果我们设置为GL_TRUE，所有数据都会被映射到0（对于有符号型signed数据是-1）到1之间。我们把它设置为GL_FALSE。
    //第五个参数 步长(Stride)，它告诉我们在连续的顶点属性组之间的间隔。这个参数的意思简单说就是从这个属性第二次出现的地方到整个数组0位置之间有多少字节
    //第六个参数 表示位置数据在缓冲中起始位置的偏移量(Offset)
//    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 3 * sizeof(float), (void *) 0);
//    glEnableVertexAttribArray(0);
    //位置属性
    glVertexAttribPointer(0, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void *) 0);
    glEnableVertexAttribArray(0);
    //颜色属性
    glVertexAttribPointer(1, 3, GL_FLOAT, GL_FALSE, 6 * sizeof(float), (void *)(3 * sizeof(float)));
    glEnableVertexAttribArray(1);
    //解除绑定
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glBindVertexArray(0);
    


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


        //每次渲染图形时都要激活一次着色器程序
        glUseProgram(shaderProgram);
        //获取CPU运行的秒数
        float timeValue = glfwGetTime();
        //让描述只在 0.0 到 0.1 之间改变
        float greenValue = (sin(timeValue) / 2.0f) + 0.5f;
        //用glGetUniformLocation查询uniform ourColor的位置值
        //如果glGetUniformLocation返回-1就代表没有找到这个位置值
        int vertexColorLocation = glGetUniformLocation(shaderProgram, "ourColor");
        //设置片段着色器源码 uniform 的值
        glUniform4f(vertexColorLocation, 0.0f, greenValue, 0.0f, 1.0f);


        glBindVertexArray(VAO);
        //第一个参数 绘制的图形类型；第二个参数 顶点数组的起始索引；第三个参数 绘制的顶点个数
        glDrawArrays(GL_TRIANGLES, 0, 3);


        //检查有没有触发什么事件（比如键盘输入、鼠标移动等）、更新窗口状态，
        //并调用对应的回调函数（可以通过回调方法手动设置）
        glfwSwapBuffers(window);
        //绘制颜色，并输出显示在屏幕上
        glfwPollEvents();
    }

    //删除着色器
    glDeleteShader(vertexShader);
    glDeleteShader(fragmentShader);
    //删除着色器程序
    glDeleteProgram(shaderProgram);
    //释放、删除之前的分配的所有资源
    glfwTerminate();
    return 0;
}