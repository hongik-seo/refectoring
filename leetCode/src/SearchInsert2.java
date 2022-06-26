import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SearchInsert2 {

    public int searchInsert2(int[] nums, int target) {

        if(nums.length==0){
            return 0;
        }

        for(int i=0; i<nums.length; i++){


            if(nums[i]>=target){

                return i;
            }else{

                nums[i]=0;
            }

        }

        if(nums[nums.length-1]==0){

            return nums.length;
        }

        return 0;
    }
}
