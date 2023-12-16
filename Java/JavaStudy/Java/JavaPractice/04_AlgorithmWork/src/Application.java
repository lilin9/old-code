package NO4_AlgorithmWork;

import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        Scanner input = new Scanner(System.in);
        Runnable runnable = () -> {
            try {
                System.out.print("\n……\n");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //input
        System.out.print("=========输入商品的价格=========\n输入：");
        String[] str = input.next().split(",");

        //sort
        System.out.print("=========正在排序=========");
        int[] array = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
        int[] sortArray = application.sort(array);
        runnable.run();
        System.out.println("=========排序完成=========");

        System.out.println(Arrays.toString(sortArray));

        //lookup
        System.out.print("\n=========输入查询的商品价格范围=========\n最低价：");
        int start = input.nextInt();
        System.out.print("最高价：");
        int end = input.nextInt();
        application.lookup(sortArray, start, end);
    }
    public int[] sort(int[] arr){
        int[] arr1 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1-i; j++){
                if (arr[j+1] < arr[j]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.arraycopy(arr, 0, arr1, 0, arr.length);

        return arr1;
    }

    private void lookup(int[] sortArray, int start, int end){
        int[] targetArray = new int[sortArray.length];

        for (int i = 0; i < sortArray.length; i++) {
            for (int j = i+1; j < sortArray.length; j++){
                if (start < sortArray[0] && end > sortArray[sortArray.length - 1]){
                    System.out.println("输入的价格不在范围内！");
                    System.exit(0);
                }
                else if (sortArray[i] == start && sortArray[j] == end){
                    int t = 0;
                    for (int k = i; k <= j; k++) targetArray[t++] = sortArray[k];
                }
                else{
                    if (sortArray[i] > start && sortArray[i-1] < start) {
                        if (sortArray[j] < end && sortArray[j+1] > end){
                            int t = 0;
                            for (int k = i; k <= j; k++) targetArray[t++] = sortArray[k];
                        }
                    }
                }
            }
        }

        System.out.println("=========" + start + "元到" + end + "元的商品价格范围=========");
        System.out.println(Arrays.toString(targetArray));
    }
}