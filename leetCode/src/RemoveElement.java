import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        if(nums.length==0){

            return 0;
        }

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){

            if(nums[i]!=val){
                list.get(nums[i]);
            }
        }

        for(int i=0; i<list.size(); i++){

            nums[i]=list.get(i);
        }

        return list.size();
    }

}
