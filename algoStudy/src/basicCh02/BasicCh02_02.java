package basicCh02;

public class BasicCh02_02 {

    public static void main(String[] args) {

        int[] arr = new int[5];
        for(int i=0; i<arr.length; i++){
            arr[i]=i+1;
            System.out.println("a["+i+"] :" +arr[i] );
        }
    }
}
