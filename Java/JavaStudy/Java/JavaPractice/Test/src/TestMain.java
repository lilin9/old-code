
/**
 * Created by LILIN on 2023/6/8/14:52:26
 */
public abstract class TestMain {
    public static void main(String[] args) {
        String str = "abcdefg";
        char[] strChar = str.toCharArray();
        int length = str.length();
        char temp;

        for (int i = 0, j = length-1; i != j; i++, j--) {
            temp = strChar[i];
            strChar[i] = strChar[j];
            strChar[j] = temp;
        }
        System.out.println("old: " + str);
        System.out.println("new: " + String.copyValueOf(strChar));
    }
}
