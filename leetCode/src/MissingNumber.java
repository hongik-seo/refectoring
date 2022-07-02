import java.util.Arrays;

public class MissingNumber {

    public static int missingNumber(int[] nums) {

        Arrays.sort(nums);

        if(Arrays.stream(nums).min().getAsInt()==0) {

            int maxVal = Arrays.stream(nums).max().getAsInt() == nums.length ? Arrays.stream(nums).max().getAsInt() : nums.length;

            //int[] reNum = new int[]{0, maxVal};

            for (int i = 0; i < maxVal; i++) {

                if (i != nums[i]) {

                    return i;
                }

            }

            return maxVal;
        }
        return 0;
    }
}
