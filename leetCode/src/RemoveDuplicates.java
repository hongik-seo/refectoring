import java.util.*;

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {

        if(nums.length==0){
            return 0;
        }

        List<Integer> list = new ArrayList<>();
        for(int i=0,j=1; j<nums.length; i++,j++){

            if(nums[i]!=nums[j]) {
                list.add(nums[i]);
            }
        }

        list.add(nums[nums.length-1]);

        for(int i=0; i<list.size(); i++) {

            nums[i]= list.get(i);
            System.out.print(nums[i]);

        }

        return list.size();
    }
}


