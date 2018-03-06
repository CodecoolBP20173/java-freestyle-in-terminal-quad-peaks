import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;

public class Word {

    public String name;
    public Coord position;
    public int hitCount;

    public void initialize(int x, int y, String name)
    {
        this.name = name;
        position = new Coord();
        position.x = x;
        position.y = y;
        show();
    }

    public void move() {
        selfClear();
        position.x += 1;
        show();
    }

    public void show()
    {
        PrimitiveType.Console.moveTo(position.x, position.y);
        for(int i = 0; i < name.length(); i++){
            System.out.print(name.charAt(i));
        }
    }

    private void selfClear() {
        PrimitiveType.Console.moveTo(position.x, position.y);
        for (int i = 0; i < name.length(); i++) {
            System.out.print(" ");
        }
    }

}