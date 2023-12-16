package eg7_Bubble_sort;
public class Bubble_sort {
    public static void main(String[] args){
        Bubble_sort text = new Bubble_sort();

        int[] nums = {25, 24, 12, 76, 101, 96, 28};

        for(int i=0; i<nums.length-1; i++){
            for(int j=0; j<nums.length-1-i; j++){
                if(nums[j+1] > nums[j]){
                    text.swap(nums, j, j+1);
                }
            }
        }

        for(int i=0; i<nums.length-1; i++){
            System.out.print(nums[i] + "\t");
        }
    }

    public void swap(int[] arg, int i, int j){
        int temp = arg[i];
        arg[i] = arg[j];
        arg[j] = temp;
    }
}
