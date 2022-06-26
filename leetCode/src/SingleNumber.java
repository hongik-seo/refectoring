import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;

public class SingleNumber {

    public int singleNumber(int[] nums) {

        Arrays.sort(nums);
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> sub = new ArrayList<>();
        int result = Integer.MIN_VALUE;
        for(int i=0, j=1; j<nums.length; i++,j++) {
            if (nums[i] == nums[j]) {
                sub = list.subList(i, j + 1);
            }
            System.out.println(sub);

            if (!sub.contains(nums[i])) {
                result = nums[i];
            }
        }
        if(result ==Integer.MIN_VALUE){

            result = nums[nums.length-1];
        }
        return result;
    }


}
