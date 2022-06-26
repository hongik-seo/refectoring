package basicCh02;

public class BasicCh02_01 {

    public static void main(String[] args) {

        int [] arr = new int[5];

        arr[0]=0;
        arr[1]=37;
        arr[2]=51;
        arr[3]=0;
        arr[4]=74;

        for(int i=0; i< arr.length; i++){

            System.out.println("a["+i+"] :" +arr[i] );

        }

    }
}
