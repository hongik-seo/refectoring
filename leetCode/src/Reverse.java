public class Reverse {
    public static int reverse(int x) {

        String xStr="";
        xStr=String.valueOf(Math.abs((long)x));
        int atFirst=0;
        int atLast=0;
        String result="";
        for(int i=0,k=xStr.length()-1; i<xStr.length();  i++,k--){

            atFirst=xStr.charAt(i)-48;
            atLast= xStr.charAt(k)-48;
            int temp1=atFirst;
            atFirst=atLast;
            atLast=temp1;
            result+=atFirst;
        }

        if(Long.parseLong(result)>=Integer.MIN_VALUE & Long.parseLong(result)<=Integer.MAX_VALUE-1){
            long a= Integer.parseInt(result);
            if(x<0){

                x= (int)(-1*a);
            }else{

                x=(int)a;
            }
            return x;
        }else {
            return 0;
        }
    }
}
