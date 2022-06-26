package basicCh02;

import java.util.Arrays;
import java.util.Scanner;

public class BasicCh02_06 {


    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        System.out.print("요솟수 :");int length = sc.nextInt();
        int[] x= new int[length];
        for(int i=0; i<x.length; i++){
            x[i] = sc.nextInt();
            System.out.println("x["+i+"] :"+x[i]);
        }

        System.out.println("=====역정령====");
        for(int i=0, j=x.length-1; i<x.length/2; i++, j--){

            int temp = x[i];
            x[i] = x[j];
            x[j]  = temp;
        }

        for(int i=0; i<x.length; i++){

            System.out.println(x[i]);
        }


    }
}
