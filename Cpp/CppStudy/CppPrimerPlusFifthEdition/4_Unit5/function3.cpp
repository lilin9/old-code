#include "iostream"

using namespace std;
/*
 * Daphne �� 10% �ĵ���Ͷ���� 100 ��Ԫ��Ҳ����˵��ÿһ���������Ͷ�ʶ�� 10%����ÿ�� 10��Ԫ��
 * ��Ϣ = 0.10 ��ԭʼ���
 * �� Cleo �� 5% �ĸ���Ͷ���� 100 ��Ԫ��Ҳ����˵����Ϣ�ǵ�ǰ��������õ���Ϣ���� 5%��
 * ��Ϣ = 0.05 x ��ǰ���
 * Cleo �ڵ�һ��Ͷ�� 100 ��Ԫ��ӯ���� 5%�����õ��� 105 ��Ԫ����һ���ӯ���� 105 ��Ԫ�� 5%������
 * 5.25 ��Ԫ���Դ����ơ����дһ�����򣬼���������Cleo ��Ͷ�ʼ�ֵ���ܳ��� Daphne ��Ͷ�ʼ�ֵ��
 * ����ʾ��ʱ�����˵�Ͷ�ʼ�ֵ
 */

const float beginMoney = 100.0;

float getDaphneMoney(float lastMoney) {
    return lastMoney + beginMoney * float(0.10);
}

float getCleoMoney(float lastMoney) {
    return lastMoney + lastMoney * float(0.05);
}

int main3() {
    //daphne �Ĵ��
    float daphne = 100;
    //cleo �Ĵ��
    float cleo = 100;
    int year = 1;

    while(true) {
        if (cleo > daphne) {
            break;
        }

        daphne += getDaphneMoney(daphne);
        cleo += getCleoMoney(cleo);

        year++;
    }

    cout << year << "���Cleo ��Ͷ�ʼ�ֵ���ܳ��� Daphne ��Ͷ�ʼ�ֵ" << endl;
    cout << "��ʱ,Cleo Ͷ�ʼ�ֵ�ǣ�" << cleo << "��Daphne ��Ͷ�ʼ�ֵ�ǣ�" << daphne << endl;
    return 0;
}