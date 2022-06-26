public class LengthOfLastWord2 {

    public int lengthOfLastWord2(String s) {

        String newStr = s.replaceAll("\\s", ",");

        String[] arr = newStr.split(",");

        return arr[arr.length-1].length();
    }
}
