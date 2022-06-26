package basicCh01;

import java.util.Scanner;

public class BasicCh01_01 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("세 정수의 최대값을 구합니다.");
        System.out.println("a의 값 : ");int a = scanner.nextInt();
        System.out.println("b의 값 : ");int b = scanner.nextInt();
        System.out.println("c의 값 : ");int c = scanner.nextInt();

        int max =a;

        if(b>max){
            max = b;
        }else if(c>max){
            max = c;
        }

        System.out.println("최댓값은  "+max+"  입니다");


    }
}
