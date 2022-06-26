package basicCh02;

public class BasicCh02_03 {

    public static void main(String[] args) {
        int[] a = new int[5];
        int[] b = {};

        System.out.print("a=");
        for(int i=0; i<a.length; i++){

            a[i]=i+1;
            System.out.print( " "+a[i] );
        }

        b=a.clone();
        b[3]=0;

        System.out.print("\nb=");
        for(int i=0; i<b.length; i++){

            System.out.print(" " +b[i] );
        }


    }
}
