package basicCh01;

import java.util.Scanner;

public class BasicCh01_04 {

    public static void main(String[] args) {

        /* 내 답
        Scanner scanner = new Scanner(System.in);
        System.out.println("1부터 n개의 합을 구합니다.");
        System.out.print("n의 값");int n = scanner.nextInt();
        int sum = n*(n+1)/2;
        System.out.println("1부터"+n+"까지의 합은 " +sum +"입니다.");
        */

        Scanner scanner = new Scanner(System.in);
        System.out.println("1부터 n개의 합을 구합니다.");
        System.out.print("n의 값");int n = scanner.nextInt();
        int sum = 0;

        for(int i=0; i<n+1; i++){

            sum+=i;
        }
        System.out.println("1부터"+n+"까지의 합은 " +sum +"입니다.");


    }

}
