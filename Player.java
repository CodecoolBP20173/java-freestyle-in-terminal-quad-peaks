import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;

import java.lang.annotation.Retention;

import com.codecool.termlib.Color;

public class Player {

    public Coord position = new Coord();
    public int specialWeaponCount;
    public int streakCount;
    public int maxLifePoints;
    public int currentLifePoints;

    private final int STREAKTHRESHOLD = 10;

    public Player(int posX, int posY, int _maxLifePoints) {
        specialWeaponCount = 5;
        streakCount = 0;
        maxLifePoints = _maxLifePoints;
        currentLifePoints = maxLifePoints;
        position.x = posX;
        position.y = posY;
        showPlayer();
    }

    public void showPlayer(){
        Coord[] playerCoords = new Coord[6];
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
        Coord pos4 = new Coord();
        pos4.x = position.x - 2;
        pos4.y = position.y;
        playerCoords[4] = pos4;
        Coord pos5 = new Coord();
        pos5.x = position.x - 3;
        pos5.y = position.y;
        playerCoords[5] = pos5;
        drawPlayer(playerCoords);
    }

    public void reset() {
        specialWeaponCount = 0;
        streakCount = 0;
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

    public void resetStreak() {
        streakCount = 0;
    }

    private void increaseSpecialWeaponCount() {
        specialWeaponCount++;
    }

    public Boolean useSpecialWeapon() {
        if (specialWeaponCount > 0) {
            specialWeaponCount--;
            return true;
        }
        return false;
    }

    private void die() {
        reset();
    }

}