#include "iostream"
#include "math.h"

using namespace std;
/*
 * 编写一个小程序，要求以几英尺几英寸的方式输入其身高，并以磅为单位输入其体重（使用3个变量来储存这些信息）。
 * 该程序报告其 BMI（Body Mass Index，体重指数）。为了计算 BMI，该程序以英寸的
 * 方式指出用户的身高（1 英尺为 12 英寸），并将以英寸为单位的身高转换为以米为单位的身高
 * （1 英寸 = 0.0254米）。然后，将以磅为单位的体重转换为以千克为单位的体重（1 千克=2.2 磅）。最后，
 * 计算相应的 BMI——体重（千克）除以身高（米）的平方。用符号常量表示各种转换因子。
 */

//1 千克=2.2 磅
const float WEIGHT_FACTOR = 2.2;
//1 英尺为 12 英寸
const float HEIGHT_FACTOR = 12;
//1 英寸 = 0.0254米
const float  M_FACTOR = 0.0254;

float getBMI(float weight , float height) {
    return pow(weight / height, 2);
}

int main2() {
    //英尺
    float foot = 0;
    //英寸
    float inch = 0;
    //体重
    float weight = 0;

    cout << "What your height in foot? ";
    cin >> foot;
    cout << "What your height in inch? ";
    cin >> inch;
    cout << "What your weight in pound? ";
    cin >> weight;

    cout << "Your BMI is " << getBMI(weight/WEIGHT_FACTOR,
                                     (foot*HEIGHT_FACTOR + inch)*M_FACTOR) << endl;

    return 0;
}