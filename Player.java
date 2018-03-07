import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;

public class Player {

    public Coord position;
    public int specialWeaponCount;
    public int streakCount;
    public int maxLifePoints;

    public Player(int x, int y, int maxLifePoints){
        position = new Coord();
        position.x = x;
        position.y = y;
        specialWeaponCount = 0;
        streakCount = 0;
        maxLifePoints = 5;
        int currentLifePoints = maxLifePoints;
        Coord[] playerCoords = new Coord[6];
        Coord pos0 = new Coord();
        pos0.x = 20;
        pos0.y = 60;
        playerCoords[0]= pos0;
        Coord pos1 = new Coord();
        pos1.x = 20;
        pos1.y = 61;
        playerCoords[1]= pos1;
        Coord pos2 = new Coord();
        pos2.x = 20;
        pos2.y = 59;
        playerCoords[2]= pos2;
        Coord pos3 = new Coord();
        pos3.x = 19;
        pos3.y = 60;
        playerCoords[3]= pos3;
        Coord pos4 = new Coord();
        pos4.x = 18;
        pos4.y = 60;
        playerCoords[4]= pos4;
        Coord pos5 = new Coord();
        pos5.x = 17;
        pos5.y = 60;
        playerCoords[5]= pos5;
        drawPlayer(playerCoords);
    }

    public void drawPlayer(Coord[] drawCoords){
        PrimitiveType.Console.setBgColor(Color.WHITE);
        for (int i=0; i<drawCoords.length; i++){
            PrimitiveType.Console.moveTo(drawCoords[i].x, drawCoords[i].y);
            System.out.print(" ");
        }
        PrimitiveType.Console.resetStyle();
    }

}