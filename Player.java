import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;

import java.lang.annotation.Retention;

import com.codecool.termlib.Color;

public class Player {

    public Coord position = new Coord();
    public int specialWeaponCount;
    public int streakCount;
    public int score;
    public int maxLifePoints;
    public int currentLifePoints;

    private int wordSpeedUpScore = 0;

    private final int STREAKTHRESHOLD = 40;

    public Player(int posX, int posY, int _maxLifePoints) {
        score = 0;
        specialWeaponCount = 3;
        streakCount = 0;
        maxLifePoints = _maxLifePoints;
        currentLifePoints = maxLifePoints;
        position.x = posX;
        position.y = posY;
        showPlayer();
    }

    public void showPlayer() {
        Coord[] playerCoords = new Coord[4];
        Coord pos0 = new Coord();
        pos0.x = position.x;
        pos0.y = position.y;
        playerCoords[0] = pos0;
        Coord pos1 = new Coord();
        pos1.x = position.x;
        pos1.y = position.y + 1;
        playerCoords[1] = pos1;
        Coord pos2 = new Coord();
        pos2.x = position.x;
        pos2.y = position.y - 1;
        playerCoords[2] = pos2;
        Coord pos3 = new Coord();
        pos3.x = position.x - 1;
        pos3.y = position.y;
        playerCoords[3] = pos3;
        drawPlayer(playerCoords);
    }

    public void reset() {
        specialWeaponCount = 0;
        streakCount = 0;
        score = 0;
        currentLifePoints = maxLifePoints;
    }

    private void drawPlayer(Coord[] drawCoords) {
        PrimitiveType.Console.setBgColor(Color.WHITE);
        for (int i = 0; i < drawCoords.length; i++) {
            PrimitiveType.Console.moveTo(drawCoords[i].x, drawCoords[i].y);
            System.out.print(" ");
        }
        PrimitiveType.Console.resetStyle();
    }

    public void takeDamage(int amount) {
        score -= 20;
        currentLifePoints -= amount;
        if (currentLifePoints <= 0) {
            die();
        }
    }

    public void increaseStreak() {
        streakCount++;
        if (streakCount == STREAKTHRESHOLD) {
            increaseSpecialWeaponCount();
            resetStreak();
        }
    }

    public void increaseScore(int amount) {
        score += amount;
        wordSpeedUpScore += amount;
        if (wordSpeedUpScore >= 20){
            PrimitiveType.wordSpeed += 0.1f;
            wordSpeedUpScore = 0;
        }
    }

    public void resetStreak() {
        streakCount = 0;
    }

    private void increaseSpecialWeaponCount() {
        specialWeaponCount++;
    }

    public Boolean useSpecialWeapon() {
        if (specialWeaponCount > 0 && PrimitiveType.sW == null) {
            specialWeaponCount--;
            return true;
        }
        return false;
    }

    private void die() {
        PrimitiveType.gameRunning = false;
        //reset();
    }

    public void renderUI() {
        String ui = String.format("%d HP |  %d EMP  | %d/%d STREAK | %d SCORE | %d TIME   ",
        currentLifePoints, specialWeaponCount, streakCount,STREAKTHRESHOLD, score, PrimitiveType.time / 1000);
        PrimitiveType.Console.moveTo(24, 0);
        System.out.print(ui);
    }

}