package myLottoCalc;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

    public static List<Map.Entry<Integer, Integer>> maxOf(ArrayList<Integer> meSelectList,ArrayList<Integer> firstSelect)  {

        Collections.sort(meSelectList);
        firstSelect.add(meSelectList.get(0));

        ArrayList<Integer> lottoList = new ArrayList<>();
        Map<Integer,Integer> finalMap = new HashMap<>();
        for(int i=1; i<=45; i++){
            lottoList.add(i);
        }
        //   System.out.println(list);
        Map<Integer,Integer> map = new HashMap<>();
        HashSet<Integer> keyList = new HashSet<>();
        int j=0;
        int count=0;
        while (meSelectList.size()!=0){
            if(meSelectList.get(0).equals(lottoList.get(j))){
                int remove=meSelectList.remove(0);
                if(lottoList.get(j)==remove){
                    count++;
                    if(remove!=firstSelect.get(0)) {
                        keyList.add(remove);
                    }else{
                        finalMap.put(remove,count);
                    }
                }
                map.put(remove,count);
            }

            j++;
            if(j==45){
                j=0;
            }
        }

        int[] arr = new int[map.size()];

        Iterator<Map.Entry<Integer, Integer>> ir = map.entrySet().iterator();

        for(int i=0; i<arr.length; i++){
            Map.Entry<Integer, Integer> it = ir.next();
            int values=it.getValue();
            arr[i]=values;
        }

        ArrayList<Integer> valueList = new ArrayList<>();
        for(int i=0, k=1+i; k<arr.length; i++,k++){
            int realValue = arr[k]-arr[i];
            valueList.add(realValue);
        }

        Iterator<Integer> reIter = keyList.iterator();

        for (Integer integer : valueList) {
            int keys = reIter.next();
            finalMap.put(keys, integer);
        }

        System.out.println("original::::"+finalMap);
        System.out.println("====================max==================");
        List<Map.Entry<Integer, Integer>> entries = finalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        for (Map.Entry<Integer, Integer> entry : entries) {
            System.out.println("내가 찍은 번호: " + entry.getKey() + ", "
                    + "횟수: " + entry.getValue());
        }
        System.out.println("====================max==================");
        return entries;
    }


    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        ArrayList<Integer> list2 = new ArrayList<>();

        list.add(2);list.add(4);list.add(20);list.add(30);list.add(37);list.add(44);

        list.add(8);list.add(10);list.add(12);list.add(19);list.add(31);list.add(36);

        list.add(9);list.add(10);list.add(20);list.add(24);list.add(30);list.add(39);

        list.add(11);list.add(12);list.add(15);list.add(20);list.add(29);list.add(34);

        list.add(2);list.add(12);list.add(14);list.add(27);list.add(28);list.add(31);

        list.add(15);list.add(17);list.add(28);list.add(27);list.add(39);list.add(45);

        list.add(1);list.add(8);list.add(11);list.add(14);list.add(17);list.add(23);

        list.add(6);list.add(15);list.add(30);list.add(40);list.add(43);list.add(44);

        list.add(1);list.add(9);list.add(25);list.add(30);list.add(34);list.add(38);

        list.add(5);list.add(10);list.add(23);list.add(30);list.add(32);list.add(35);

        list.add(10);list.add(18);list.add(20);list.add(23);list.add(26);list.add(27);

        list.add(6);list.add(11);list.add(20);list.add(27);list.add(31);list.add(35);

        list.add(19);list.add(25);list.add(26);list.add(31);list.add(34);list.add(41);

        list.add(17);list.add(21);list.add(29);list.add(37);list.add(44);list.add(45);

        list.add(18);list.add(19);list.add(20);list.add(33);list.add(37);list.add(40);

        list.add(2);list.add(13);list.add(17);list.add(19);list.add(22);list.add(30);

        list.add(5);list.add(9);list.add(20);list.add(26);list.add(27);list.add(37);

        list.add(11);list.add(13);list.add(16);list.add(33);list.add(27);list.add(37);

        list.add(7);list.add(12);list.add(21);list.add(22);list.add(34);list.add(37);

        list.add(1);list.add(11);list.add(14);list.add(33);list.add(34);list.add(44);

        System.out.println(maxOf(list,list2));

    }

}
