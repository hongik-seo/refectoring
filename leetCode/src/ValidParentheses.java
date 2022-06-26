import java.util.*;

public class ValidParentheses {

    public boolean isValid(String s) {
        if(s.length()%2==1){
            return false;
        }

        Deque<String> deque = new LinkedList<>();
        for(int i=0; i<s.length(); i++){
            String pushStr = String.valueOf(s.charAt(i));
            if(pushStr.equals("(")  || pushStr.equals("[") || pushStr.equals("{")){
                deque.push(pushStr);
            } else {
                if(deque.size()==0){
                    return false;
                }else{
                    String str =deque.getFirst();

                    if(str.equals("(") && pushStr.equals(")")){
                        deque.pop();
                    }else if(str.equals("[") && pushStr.equals("]")){
                        deque.pop();
                    }else if( str.equals("{") && pushStr.equals("}")){
                        deque.pop();
                    }else{
                        break;
                    }
                }
            }
        }

        if(deque.size()==0){
            return true;
        }else{
            return false;
        }
    }

}
