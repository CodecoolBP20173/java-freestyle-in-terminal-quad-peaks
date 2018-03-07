import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import javax.lang.model.type.PrimitiveType;

public class Player {

    public Coord position;
    public int specialWeaponCount;
    public int streakCount;
    public int maxLifePoints;

    public Player(int x, int y, int specialWeaponCount, int streakCount, int maxLifePoints){
        position = new Coord();
        position.x = x;
        position.y = y;
        specialWeaponCount = 0;
        streakCount = 0;
        maxLifePoints = 5;
        int currentLifePoints = maxLifePoints;
        Coord[] playerCoords = new Coord[6];
        Coord pos0 = new Coord();
        pos0.x = 2;
        pos0.y = 3;
        playerCoords[0]= pos0;
        Coord pos1 = new Coord();
        pos1.x = 2;
        pos1.y = 4;
        playerCoords[1]= pos1;
        Coord pos2 = new Coord();
        pos2.x = 2;
        pos2.y = 5;
        playerCoords[2]= pos2;
        Coord pos3 = new Coord();
        pos3.x = 3;
        pos3.y = 2;
        playerCoords[3]= pos3;
        Coord pos4 = new Coord();
        pos4.x = 3;
        pos4.y = 2;
        playerCoords[4]= pos4;
        Coord pos5 = new Coord();
        pos5.x = 3;
        pos5.y = 2;
        playerCoords[5]= pos5;
        Coord pos6 = new Coord();
        pos6.x = 4;
        pos6.y = 2;
        playerCoords[6]= pos6;

    }


    public void position() {
        Console.clearScreen();
        Word word = new Word();
        word.initialize(100, 50, "◢☗◣");
    }

    public void drawPlayer(Coord[] drawCoords){
        PrimitiveType.Console.setBg(Color.WHITE);
        for (int i=0; i<drawCoords.length(); i++){
            PrimitiveType.Console.moveTo(drawCoords[i].x, drawCoords[i].y);
            System.out.print(" ");
        }
        PrimitiveType.Console.resetStyle();
    }

/*
    public void streakCount (){

    }

    public void specialWeaponCount(){

    }

    public void lifePoints(){

    }
    */
}