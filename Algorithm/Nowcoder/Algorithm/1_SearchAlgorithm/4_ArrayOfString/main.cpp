#include "iostream"
#include "vector"

using namespace std;

class Solution {
public:
    vector<string> Permutation(string str) {

        if (str == "") {
            return vector<string>{};
        }

        if (str.length() == 1) {
            return vector<string>{str};
        }

        vector<string> result;
        getString(str, 0, result);

        return result;
    }

    void getString(string str, int level, vector<string>& result) {
    }
};

int main4() {
    return 0;
}