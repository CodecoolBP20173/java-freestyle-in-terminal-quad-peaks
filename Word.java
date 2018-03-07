import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;

public class Word {

    public String name;
    public Coord position;
    public int hitCount;
    private Boolean destroyed = false;
    public Word(int x, int y, String name)
    {
        this.name = name;
        position = new Coord();
        position.x = x;
        position.y = y;
        show();
    }

    public Boolean wordHitHandler(char typedChar) {
        if(destroyed)
        {
            return false;
        }
        if (name.charAt(hitCount) == typedChar){
            hitCount ++;
            if (hitCount == name.length())
            {
                die();
            }
            else {
                refresh();
            }
            return true;
        }
        return false;
    }

    public void die(){
        selfClear();
        destroyed = true;
    }

    public void refresh() {
        selfClear();
        show();
    }

    public void move() {
        if (destroyed)
        {
            return;
        }
        selfClear();
        position.x += 1;
        show();
    }

    public void show(){
        PrimitiveType.Console.moveTo(position.x, position.y);
        PrimitiveType.Console.setColor(Color.RED);
        for(int i = 0; i < name.length(); i++){
            if (i == hitCount){
                PrimitiveType.Console.setColor(Color.WHITE);
            }
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