import java.util.*;

public class SearchInsert {

    public int searchInsert(int[] nums, int target) {

        if(nums.length==0){
            return 0;
        }
        Deque<Integer> dq = new LinkedList<>();
        for(int i=0; i<nums.length; i++){
            dq.add(nums[i]);
            if(nums[i]==target){

                return i;
            }
            if(dq.poll()>target){

                return i;
            }
        }

        if(dq.size()==0) {

            return nums.length;
        }

        return 0;
    }
}
