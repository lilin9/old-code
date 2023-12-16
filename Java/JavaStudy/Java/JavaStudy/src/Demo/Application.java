package Demo;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 3, 8, 5};

        for (int i = 0; i < nums.length; i++) {
            for(int j=0; j<nums.length-1-i; j++){
                if (nums[i] > nums[i+1]){
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
