#include "iostream"
#include "cstring"

using namespace std;

//×Ö·û´®µÄÉùÃ÷
void test1() {
    const char *str = "hello world";
    string s1(str);
    cout << "s1 = " << s1 << endl;

    string s2(s1);
    cout << "s2 = " << s2 << endl;

    string s3(10, 'h');
    cout << "s3 = " << s3 << endl;
}

//×Ö·û´®µÄÆ´½Ó
void test2() {
    string s1 = "ÎÒ°®";
    s1 = s1 + "²Ô¾®¿Õ";
    cout << s1 << endl;

    s1.append("£¬Ò²°®ÍæÅ¼½ã½ã");
    cout << s1 << endl;
}

//×Ö·û´®µÄ²éÕÒ
void test3() {
    string str = "i love you ten thousand years old";
    cout << str.find('o') << endl;
    cout << str.rfind('o') << endl;
}

//×Ö·û´®µÄÌæ»»
void test4() {
    string str = "i love you ten thousand years old";
    str.replace(2, 4, "hide");
    cout << str << endl;
}

//×Ö·û´®±È½Ï
void test5() {
    string str1 = "love";
    string str2 = "like";
    if (str1.compare(str2) == 1) {
        cout << str1 << " != " << str2;
    } else {
        cout << str1 << " = " << str2;
    }
}

//´æÈ¡×Ö·û´®
void test6() {
    string str = "love";

    //·½Ê½Ò»
    for (int i = 0; i < str.size(); ++i) {
        cout << str[i] << "\t";
    }
    cout << endl;

    //·½Ê½¶ş
    for (int i = 0; i < str.size(); ++i) {
        cout << str.at(i) << "\t";
    }
    cout << endl;
}

//×Ö·û´®µÄ²åÈëÓëÉ¾³ı
void test7() {
    string str = "love";
    //²åÈë
    str.insert(str.length(), "like");
    cout << str << endl;

    //É¾³ı
    str.erase(3, str.length()-4);
    cout << str << endl;
}

//Çó×Ö·û´®µÄ×Ö´®
void test8() {
    string str = "tom@gmail.com";
    int index = str.find('@');
    cout << str.substr(0, index) << endl;
}

int main() {
    test8();
    return 0;
}