import com.codecool.termlib.Terminal;

import java.io.IOException;

import com.codecool.termlib.Coord;

class PrimitiveType {

    public static Terminal Console = new Terminal();

    private static int wordSpeed = 5;

    private static long programStart;
    private static long time;
    private static long deltaTime;
    private static long deltaSum;

    public static void main(String[] args) {
        Console.clearScreen();
        programStart = System.currentTimeMillis();
        Word word = new Word(0, 50, "lolka");
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

}