import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import java.util.Arrays;

import com.codecool.termlib.Color;

public class DynamicWordArray {
    static Word[] wordList = new Word[0];

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

    public static void removeWordsAtPosition(Coord pos)
    {
        PrimitiveType.targetWord = -1;
        for (int i = 0; i < wordList.length; i++){
            if (wordList[i].position.x == pos.x || wordList[i].position.x == pos.x - 1 || wordList[i].position.x == pos.x + 1){
                removeWord(wordList[i]);
            }
        }
    }

    public static void insertWord(Word wordObject, int index){
        Word[] newWordList = Arrays.copyOf(wordList, wordList.length+1);
        int index2 = 0;
        for(int i=0; i<newWordList.length; i++){
            if (i == index){
                newWordList[i] = wordObject;
                continue;
            }
            newWordList[i] = wordList[index2]; 
            index2++;
        }
        wordList = newWordList;
    }
}