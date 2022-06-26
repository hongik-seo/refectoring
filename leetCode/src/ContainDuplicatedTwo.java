import com.sun.xml.internal.bind.v2.runtime.output.IndentingUTF8XmlOutput;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ContainDuplicatedTwo {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        int distinctNum=(int)Arrays.stream(nums).distinct().count();

        if(distinctNum==nums.length){
            return false;
        }

        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());

        Deque<Integer> queue = new LinkedList<>();

        Set<Integer> set=Arrays.stream(nums).boxed().collect(Collectors.toSet());

        List<Integer> setList=set.stream().mapToInt(Integer::new).boxed().collect(Collectors.toList());

        System.out.println(list);

        System.out.println(setList);


        for(int i=0; i<list.size(); i++){

            queue.add(list.get(i));
        }

        for(int i=0; i<setList.size(); i++){

            int quval =queue.poll();

            if(quval==list.get(i)){

                System.out.println(list.get(i));
            }


        }

        list.clear();
        System.out.println("que::::::"+queue);

        for(int i=0; i<nums.length; i++){

            if(nums[i]==queue.element()){

                list.add(i);
            }
        }

        System.out.println(list);

        System.out.println(nums[list.get(0)]);

        System.out.println(nums[list.get(1)]);

        if(nums[list.get(0)]==nums[list.get(1)] && Math.abs(list.get(0)-list.get(1))<=k){

            return true;
        }

        return false;
    }
}
