package eg5_Sparse_array;
public class Sparse_array{
    public static void main(String[] args){

        // 创建一个二维数组     0：没有棋子；     1：黑棋；    2：白棋
        int[][] array1 = new int[11][11];
        array1[1][2] = 1;
        array1[2][3] = 2;

        System.out.println("========输出原数组========");
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                System.out.print(array1[i][j] + "\t");
            }
            System.out.println();
        }

        // 有效元素的个数
        int sum = 0;
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                if(array1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("\n有效值的个数是" + sum + "个！");

        // 转换为稀疏数组，并且保存
        int[][] array2 = new int[sum+1][3];

        array2[0][0] = 11;
        array2[0][1] = 11;
        array2[0][2] = sum;

        int count = 0;
        for(int i=0; i<array1.length; i++){
            for(int j=0; j<array1.length; j++){
                if(array1[i][j] != 0){
                    count++;
                    array2[count][0] = i;
                    array2[count][1] = j;
                    array2[count][2] = array1[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("========输出稀疏数组========");
        for (int[] ints : array2) {
            System.out.println(ints[0] + "\t"
                    + ints[1] + "\t"
                    + ints[2] + "\t");
        }
    }
}
