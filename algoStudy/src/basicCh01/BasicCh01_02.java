package basicCh01;

import java.util.Scanner;

public class BasicCh01_02 {

    public static int medium(int a, int b, int c){

       /* 내코드
       if(a>=b && b>=c && c<=a){
           return b;
        }else if(a>=b && b<=c && c>=a){
           return a;
        }else if(a>=b && b<=c && c<=a){
           return c;
        }else if(a<=b && b>=c && c>=a){
           return c;
        }else if(a<=b && b>=c && c<=a ){
           return a;
        }else if(a<=b && b<=c && c>=a){
           return b;
        }else{
           return b;
       }

       */

       if( ( b>=a && c<=a ) || ( b<=a && c>=a )){
           return a;
       }else if( (a>b && c<b) || (a<b && c>b) ){

           return b;
       }else {
           return c;
       }

    }

    public static void main(String[] args) {

        Scanner scanner =new Scanner(System.in);

        System.out.println("세 정수의 중앙값을 구합니다.");
        System.out.println("a의 값 : ");int a = scanner.nextInt();
        System.out.println("b의 값 : ");int b = scanner.nextInt();
        System.out.println("c의 값 : ");int c = scanner.nextInt();
        System.out.println("중앙값은  "+medium(a,b,c) + "입니다");
    }
}
