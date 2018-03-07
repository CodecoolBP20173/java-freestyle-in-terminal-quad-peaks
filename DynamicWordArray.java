import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;

public class DynamicWordArray {
    static Word[] wordList;
    public static void addWord(Word wordObject){
        Word[] newWordList = Arrays.copyOf(wordList, wordList.length+1);
        newWordList[newWordList.length-1] = wordObject;
        wordList = newWordList;
    }

    public static void removeWord(Word wordObjectToRemove){
        Word[] newWordList = new Word[wordList.length-1];
        int correction = 0;
        for(int i=0; i<wordList.length; i++){
            if (wordList[i] == wordObjectToRemove){
                correction += 1;
                continue;
            } 
            newWordList[i-correction] = wordList[i];
        }
        wordList = newWordList;

    }

}