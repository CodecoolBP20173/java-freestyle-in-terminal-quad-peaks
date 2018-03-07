import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;

public class Word {

    public String name;
    public Coord position;
    public int hitCount;

    public Word(int x, int y, String name)
    {
        this.name = name;
        position = new Coord();
        position.x = x;
        position.y = y;
        show();
    }

    public wordHitHandler(char typedChar) {
        for (int i = 0; i < name.length(); i++){
            if (name.charAt(i) == typedChar){
                hitCount ++;
                PrimitiveType.Console.setColor(RED);
                
            }
        }


    }

    private destroyed(){
        if (hitCount == name.length()) {
            selfClear();
        }

    }

    public void refresh() {
        show();
        PrimitiveType.Console.setColor(Color.WHITE);
    
    }


    public void move() {
        selfClear();
        position.x += 1;
        show();
    }

    public void show(){
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