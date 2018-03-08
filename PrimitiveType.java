import com.codecool.termlib.Terminal;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import com.codecool.termlib.Coord;
import java.util.Arrays;

class PrimitiveType {

    public static Terminal Console = new Terminal();

    public static float wordSpeed = 0.4f;
    private static float swSpeed = 5f;

    private static long programStart;
    public static long time;
    private static long deltaTime;
    private static long wordDeltaSum;
    private static long sWDeltaSum;
    public static int randomWordIndex;
    public static int targetWord = -1;
    public static Player player;
    public static SpecialWeapon sW;
    public static Boolean gameRunning = true;

    public static void initWords(int numOfWords) {
        int horizontalPosition = 0;
        int verticalPosition = numOfWords;
        for (int i = 0; i < numOfWords; i++) {
            randomWordIndex = getRandomInt(0, Word.nameList.length);
            Word word = new Word(verticalPosition, horizontalPosition, Word.nameList[randomWordIndex]);
            DynamicWordArray.addWord(word);
            horizontalPosition += 10;
            verticalPosition--;
        }
    }

    public static void main(String[] args) {
        Console.clearScreen();
        player = new Player(23, 40, 4);
        programStart = System.currentTimeMillis();
        PrimitiveType.initWords(4);
        Boolean quit = false;
        while (!quit) {
            if (gameRunning) {
                deltaTime = (System.currentTimeMillis() - programStart) - time;
                time = System.currentTimeMillis() - programStart;
                wordDeltaSum += deltaTime;
                sWDeltaSum += deltaTime;

                //INPUT CHECK
                Character c = tryToRead();
                if (c != null && sW == null) {
                    if (c == '0') {
                        quit = true;
                    } else if (c == ' ') {
                        if (player.useSpecialWeapon()) {
                            sW = new SpecialWeapon();
                        }
                    } else {
                        if (targetWord == -1) {
                            for (int i = 0; i < DynamicWordArray.wordList.length; i++) { // Here i have to work
                                if (DynamicWordArray.wordList[i].wordHitHandler(c) == Hitvalue.HIT) {
                                    player.increaseStreak();
                                    targetWord = i;
                                    break;
                                }
                                player.resetStreak();
                            }
                        } else {
                            Word targetWordObject = DynamicWordArray.wordList[targetWord];
                            Hitvalue resultOfHit = targetWordObject.wordHitHandler(c);
                            if (resultOfHit == Hitvalue.DESTROYED) {
                                player.increaseScore(targetWordObject.name.length());
                                player.increaseStreak();
                                targetWord = -1;
                            } else if (resultOfHit == Hitvalue.MISS) {
                                player.resetStreak();
                            } else if (resultOfHit == Hitvalue.HIT) {
                                player.increaseStreak();
                            }
                        }
                    }
                }

                if (wordDeltaSum >= 500 / wordSpeed) {
                    for (int i = 0; i < DynamicWordArray.wordList.length; i++) {
                        DynamicWordArray.wordList[i].move();
                    }

                    if (DynamicWordArray.wordList.length < 6) {
                        randomWordIndex = getRandomInt(0, Word.nameList.length);
                        int randomWordPosition = getRandomInt(0, 65);
                        Word word = new Word(0, randomWordPosition, Word.nameList[randomWordIndex]);
                        DynamicWordArray.addWord(word);

                    }
                    player.showPlayer();

                    wordDeltaSum = 0;
                }
                if (sWDeltaSum >= 500 / swSpeed) {
                    if (sW != null) {
                        sW.move();
                    }
                    sWDeltaSum = 0;
                }

                player.renderUI();
            } else{
                Console.clearScreen();
                Console.moveTo(12,36);
                System.out.print("DEAD");
                Console.moveTo(13,36);
                System.out.print(String.format("SCORE: %d", player.score));
                quit = true;
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