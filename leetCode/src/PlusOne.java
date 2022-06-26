import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int[] temp=digits.clone();
        for(int i=digits.length-1; i>=0; i--) { //digits.length==3 i==3
            digits[i]=digits[i]+1;
            if(temp[i]==9){
                digits[i]=0;
            }else{
                break;
            }
        }

        if(digits[0]==0){
            int[] realArr= Arrays.copyOf(digits,digits.length+1);;
            realArr[0]=1;
            for(int i=1; i<realArr.length; i++){

                realArr[i]=0;
            }
            return realArr;
        }
        return digits;
    }
}
