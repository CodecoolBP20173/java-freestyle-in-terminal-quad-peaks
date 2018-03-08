import com.codecool.termlib.Terminal;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import com.codecool.termlib.Coord;
import java.util.Arrays;

class PrimitiveType {

    public static Terminal Console = new Terminal();

    private static int wordSpeed = 1;

    private static long programStart;
    private static long time;
    private static long deltaTime;
    private static long deltaSum;
    public static int randomWordIndex;
    public static Player player = new Player(5,5,10);
    

    public static void initWords(int numOfWords){
        int horizontalPosition = 0;
        for (int i = 0; i < numOfWords; i++ ){
            randomWordIndex = getRandomInt(0, Word.nameList.length);
            Word word = new Word(0, horizontalPosition, Word.nameList[randomWordIndex]);
            DynamicWordArray.addWord(word);
            horizontalPosition = horizontalPosition + 10;
        }
    }

    public static void main(String[] args) {
        Console.clearScreen();
        programStart = System.currentTimeMillis();
        Player player = new Player(24,40,5);
        Word word = new Word(0, 50, "lolka");
        PrimitiveType.initWords(6);
        Boolean quit = false;
        while (!quit) {
            deltaTime = (System.currentTimeMillis() - programStart) - time;
            time = System.currentTimeMillis() - programStart;
            deltaSum += deltaTime;

            //INPUT CHECK
            Character c = tryToRead();
            if (c != null) {
                if (c == '0')
                {
                    quit = true;
                }
                for (int i = 0; i < DynamicWordArray.wordList.length; i++ ) {
                    DynamicWordArray.wordList[i].wordHitHandler(c);
                }
                //System.out.println(Arrays.toString(DynamicWordArray.wordList));
                int horizontalPosition = 0;
                for (int i = 0; i < DynamicWordArray.wordList.length; i++ ) {
                    
                    if (DynamicWordArray.wordList[i].destroyed) {
                        DynamicWordArray.removeWord(DynamicWordArray.wordList[i]);
                        randomWordIndex = getRandomInt(0, Word.nameList.length);
                        Word word = new Word(0, horizontalPosition, Word.nameList[randomWordIndex]);
                        DynamicWordArray.insertWord(word, i);
                        horizontalPosition = 0;
                    }
                    horizontalPosition = horizontalPosition + 10;    
                }   
            }

            if (deltaSum >= 500 / wordSpeed) {
                for (int i = 0; i < DynamicWordArray.wordList.length; i++ ) {
                    DynamicWordArray.wordList[i].move();
                }
                deltaSum = 0;
            }
        }
    }

    private static Character tryToRead() {
        try {
            if (System.in.available() > 0) {
                return (char) System.in.read();
            }
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }

    private static int getRandomInt(int start, int end) {
        return ThreadLocalRandom.current().nextInt(start, end);
    }
}