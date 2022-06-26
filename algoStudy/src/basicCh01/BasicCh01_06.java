package basicCh01;

public class BasicCh01_06 {

    public static void main(String[] args) {
        System.out.println("======곱셈표========");
        for(int i=1; i<=9; i++){
            for(int j=1; j<=9; j++){
                System.out.printf("%3d",i*j);
            }
           System.out.println();
        }

        System.out.println("======덧셈표========");
        for(int i=0; i<=9; i++){
            for(int j=0; j<=9; j++){
                System.out.printf("%3d",i+j);
            }
            System.out.println();
        }
        System.out.println("======정사각형========");
        for(int i=0; i<=9; i++){
            for(int j=0; j<=9; j++){
                System.out.printf("%3s","*");
            }
            System.out.println();
        }

        System.out.println("======피라미드========");
        for (int i = 0; i < 8; i++) {
            for (int j = 1; j < 8- i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i * 2 + 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
