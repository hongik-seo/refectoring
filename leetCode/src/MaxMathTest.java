import java.util.*;

public class MaxMathTest {

    public int[] maxMathTest(int[] answers) {

        int[] one =   {1, 2, 3, 4, 5};
        int[] two =   {2, 1, 2, 3, 2, 4,2,5};
        int[] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        List<Integer> oneList = Arrays.asList(Arrays.stream(one).boxed().toArray(Integer[]::new));
        List<Integer> twoList = Arrays.asList(Arrays.stream(two).boxed().toArray(Integer[]::new));
        List<Integer> threeList = Arrays.asList(Arrays.stream(three).boxed().toArray(Integer[]::new));

        List<Integer> addOneList = new ArrayList<>();
        List<Integer> addTowList = new ArrayList<>();
        List<Integer> addThreeList = new ArrayList<>();

        addOneList.addAll(oneList);
        addTowList.addAll(twoList);
        addThreeList.addAll(threeList);

        for(int i=0; i< answers.length; i++){

            if(i==addOneList.size()){
                addOneList.addAll(i, oneList);
            }
            if(i==addTowList.size()){
                addTowList.addAll(i, twoList);
            }
            if(i==addThreeList.size()){
                addThreeList.addAll(i, threeList);
            }

        }

        oneList=  addOneList.subList(0, answers.length);
        twoList=  addTowList.subList(0, answers.length);
        threeList=  addThreeList.subList(0, answers.length);

        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for (int i = 0; i < answers.length; i++) {

            if (oneList.get(i) == answers[i]) {
                count1++;
            }
            if (twoList.get(i) == answers[i]) {
                count2++;
            }

            if (threeList.get(i) == answers[i]) {
                count3++;
            }

        }
        int max=Math.max(count1,Math.max(count2,count3));

        int[] answer ={};

        if(max==count1){

            answer=new int[]{1};
        }

        if(max==count2){

            answer=new int[]{2};
        }

        if(max==count3){

            answer=new int[]{3};
        }

        if(max==count1 && max==count2){

            answer=new int[]{1,2};
        }

        if(max==count1 && max==count3){

            answer=new int[]{1,3};
        }

        if(max==count2 && max==count3){

            answer=new int[]{2,3};
        }

        if(max==count1 && max==count2 && max==count3){

            answer=new int[]{1,2,3};
        }

        return answer;
    }

}
