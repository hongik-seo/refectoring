public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        String newStr=s.trim();
        newStr = newStr.replaceAll("\\s", ",");
        if(!newStr.contains(",")){
            return newStr.length();
        }
        int fromIdx = 0;
        for(int i=0; i<newStr.length();  i++){
            if(newStr.charAt(i)==','){
                fromIdx=i;
            }
        }

        String finalStr= newStr.substring(fromIdx,newStr.length()-1);
        return finalStr.length();
    }
}
