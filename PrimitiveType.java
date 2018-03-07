import com.codecool.termlib.Terminal;
import java.util.concurrent.ThreadLocalRandom;

import java.io.IOException;

import com.codecool.termlib.Coord;

class PrimitiveType {

    public static Terminal Console = new Terminal();

    private static int wordSpeed = 5;

    private static long programStart;
    private static long time;
    private static long deltaTime;
    private static long deltaSum;
    public static int randomWordIndex;

    public static void main(String[] args) {
        Console.clearScreen();
        randomWordIndex = getRandomInt(0, Word.nameList.length);
        programStart = System.currentTimeMillis();
        Word word = new Word(0, 50, Word.nameList[randomWordIndex]);
        Boolean quit = false;
        while (!quit) {
            deltaTime = (System.currentTimeMillis() - programStart) - time;
            time = System.currentTimeMillis() - programStart;
            deltaSum += deltaTime;

            //INPUT CHECK
            Character c = tryToRead();
            if (c != null) {
                if (c == 'q')
                {
                    quit = true;
                }
                word.wordHitHandler(c);
                if (word.destroyed) {
                    randomWordIndex = getRandomInt(0, Word.nameList.length);
                    word = new Word(0, 50, Word.nameList[randomWordIndex]);
                }
            }

            if (deltaSum >= 500 / wordSpeed) {
                word.move();
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