#include "iostream"

using namespace std;

/*
 * 编写一个程序，要求用户输入最多 10 个高尔夫成绩，并将其储存在一个数组中。程序允许用户提早
 * 结束输入，并在一行上显示所有成绩，然后报告平均成绩。请使用 3 个数组处理函数来分别进行输入、
 * 显示和计算平均成绩。
 */

float getAverageScores(const float *scoresArr) {
    int count = 0;
    float sum = 0;

    for (int i = 0; i < 10; ++i) {
        if (scoresArr[i] == -1) {
            break;
        }

        sum += scoresArr[i];
        count ++;
    }

    return sum / (float) count;
}

void outAverageScores(float averageScores, const float *scoresArr) {
    cout << "Your golf scores is: " << endl;
    for (int i = 0; i < 10; ++i) {
        if (scoresArr[i] == -1) {
            break;
        }
        cout << scoresArr[i] << " ";
    }
    cout << "\nYour golf average scores is " << averageScores << endl;
    delete [] scoresArr;
}

//输入平均成绩
float* inAverageScores() {
    auto *scoresArr = new float [10];
    for (int i = 0; i < 10; ++i) {
        float scores;
        cout << "Enter your golf scores (0 to exit):  ";
        cin >> scores;

        if (scores == 0) {
            scoresArr[i] = -1;
            break;
        }

        scoresArr[i] = scores;
    }

    return scoresArr;
}

int main2() {
    float *scoresArr = inAverageScores();
    float averageScores = getAverageScores(scoresArr);
    outAverageScores(averageScores, scoresArr);

    return 0;
}