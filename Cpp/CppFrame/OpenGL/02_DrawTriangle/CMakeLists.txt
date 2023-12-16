cmake_minimum_required(VERSION 3.25)
project(OpenGL)

set(CMAKE_CXX_STANDARD 17)

add_executable(OpenGL src/glad.c main.cpp)

INCLUDE_DIRECTORIES(include)
link_directories(lib)
target_link_libraries(OpenGL ${PROJECT_SOURCE_DIR}/lib/glfw3.dll)