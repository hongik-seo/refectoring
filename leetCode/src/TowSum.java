public class TowSum {

    public int[] twoSum(int[] nums, int target) {
        int[] answer=new int[2];

        for(int i=0, j=1,k=nums.length-1; j<nums.length; i++,j++,k--){

            if( (nums[i] +nums[j])==target ) {

                answer = new int[]{ i, j };
                break;
            }else if( (nums[i]+nums[k]) == target ){
                answer= new int[]{ i, k };
                break;
            }else if( (nums[j]+nums[k]) == target ){
                answer= new int[]{ j, k };
                break;
            }else if( (nums[0]+nums[k]) == target ){
                answer=new int[]{ 0,k };
                break;
            }else if( (nums[i]+ nums[nums.length-1] ) == target ){
                answer= new int[] { i, nums.length-1 };
                break;
            }

        }
        return answer;
    }
}
