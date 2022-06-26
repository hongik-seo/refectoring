public class MySqrt {

    public static int mySqrt(int x) {
        int div=1;
        long mysqrt=0L;
        for(int i=0; i<x; i++){
            mysqrt = (x / (div + i));

            long multi=mysqrt*mysqrt;
            if(multi<=x){

                break;
            }
        }
        return (int)mysqrt;
    }
}
