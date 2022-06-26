public class Palindrome {

    public  boolean isPalindrome(int x) {

        String str=String.valueOf(x);

        StringBuffer sb=new StringBuffer();

        sb.append(str);

        sb.reverse();

        if(str.equals(sb.toString())){

            return true;
        }else {
            return false;
        }

    }
}
