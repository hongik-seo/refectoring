import java.util.Arrays;

public class IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {

        Boolean[] bool = new Boolean[s.length()];

        for(int i=0; i<s.length(); i++){

            int sIdx= s.indexOf(s.charAt(i));
            int tIdx= t.indexOf(t.charAt(i));

            if(sIdx==tIdx){

                bool[i]=true;
            }else{
                bool[i]=false;
            }

        }

        if(Arrays.asList(bool).contains(false)){
            return false;
        }else{
            return true;
        }

    }
}
