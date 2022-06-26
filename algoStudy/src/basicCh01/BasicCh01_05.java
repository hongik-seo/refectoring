package basicCh01;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class BasicCh01_05 {

    public static int toStr(int n){
        String str = String.valueOf(n);
        return str.length();

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("2자리의 정수를 입력하시오");
        int no =0;
        while (true){
            int n= scanner.nextInt();
            if(toStr(n)==2){
                no=n;
                break;
            }else {
                continue;
            }
        }
        System.out.println("변수 no의 값은 "+no +"되었습니다.");
    }
}
