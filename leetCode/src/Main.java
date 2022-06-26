class Main{

    public static void main(String[] args) {

        ContainDuplicatedTwo containDuplicatedTwo = new ContainDuplicatedTwo();

        int[] nums = {1,0,1,1};
        int k = 1;

        boolean result = containDuplicatedTwo.containsNearbyDuplicate(nums, k);

        System.out.println(result);
    }
}
