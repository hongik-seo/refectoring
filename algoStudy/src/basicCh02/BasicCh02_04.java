package basicCh02;

import java.util.Scanner;

public class BasicCh02_04 {

    public static int maxOf(int[] a){

        int max= 0;
        for(int i=0, j=1; j<a.length; i++,j++){

            if(a[i]>=a[j]){
                max = a[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] height =new int[num];

        System.out.println("사람 수: " +  num);
        for(int i=0; i<height.length; i++){
            height[i]=scanner.nextInt();
            System.out.println("height["+i+"] :"+height[i]);
        }

        System.out.println("최댓값은 "+maxOf(height)+" 입니다");

    }
}
