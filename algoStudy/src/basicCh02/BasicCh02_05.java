package basicCh02;

import java.util.Random;
import java.util.Scanner;

public class BasicCh02_05 {
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
        System.out.print("사람 수 :");
        int num = scanner.nextInt();
        int[] height =new int[num];
        Random random = new Random();
        for(int i=0; i<height.length; i++){
            height[i]=random.nextInt(90);
            System.out.println("height["+i+"] :"+height[i]);
        }

        System.out.println("최댓값은 "+maxOf(height)+" 입니다");

    }
}
