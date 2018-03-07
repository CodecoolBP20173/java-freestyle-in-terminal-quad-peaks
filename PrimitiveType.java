import com.codecool.termlib.Terminal;

import java.util.Timer;
import java.util.TimerTask;

import com.codecool.termlib.Coord;

class PrimitiveType {

    public static Terminal Console = new Terminal();

    private static int wordSpeed = 5;

    public static void main(String[] args) {
        Console.clearScreen();
        Word word = new Word();
        word.initialize(0, 50, "sajt");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                word.move();
            }
        };
        timer.schedule(task, 0L, 1000L / wordSpeed);
    }

    private Character tryToRead() {
        try {
            if (System.in.available() > 0) {
                return (char)System.in.read();
            }
        }
        catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
        return null;
    }
    
}