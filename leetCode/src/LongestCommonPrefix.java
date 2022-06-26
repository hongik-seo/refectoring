public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String answer="";
        String[] tempArr= new String[strs.length];
        int minLeng = strs[0].length();
        for(int i=1; i<strs.length; i++){
            if(minLeng>= strs[i].length()){
                minLeng=(strs[i].length());
            }
        }
        for(int i=0; i<strs.length; i++){
            tempArr[i]=strs[i].substring(0,minLeng);
        }
        String varWord ="";
        String varAfterWord="";
        String firstWord ="";
        String lastWordAfter ="";

        outer :for(int i=0, k=1  ; k<tempArr.length; i++, k++){

            innner :for(int j=0; j<tempArr[0].length(); j++){

                firstWord = String.valueOf(tempArr[0].charAt(j));
                varWord=String.valueOf(tempArr[i].charAt(j));
                varAfterWord = String.valueOf(tempArr[k].charAt(j));
                lastWordAfter = String.valueOf(tempArr[tempArr.length-1].charAt(j));

                if( firstWord.equals(lastWordAfter)
                        && varWord.equals(varAfterWord) && varWord.equals(lastWordAfter)
                        && firstWord.equals(varWord)
                )
                {
                    answer=tempArr[0].substring(0,j+1);

                }else
                {
                    break outer;
                }

            }

        }
        if(tempArr.length==1){

            answer=tempArr[0];
        }
        return answer;
    }
}
