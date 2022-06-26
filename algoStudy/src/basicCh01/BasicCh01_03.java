package basicCh01;

import java.util.Scanner;

public class BasicCh01_03 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("정수를 입력하시오 ");
        int a = sc.nextInt();

        if (a > 0) {
            System.out.println("이 수는 정수입니다.");
        } else if (a < 0) {
            System.out.println("이 수는 음수입니다.");
        } else {
            System.out.println("이 수는 0입니다.");
        }

    }
}
