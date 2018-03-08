import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;


public class SpecialWeapon {

    private Coord position;

    public SpecialWeapon() {
        position = new Coord();
        position.x = 20;
        position.y = 0;
        show();
    }

    public void move()
    {
        selfClear();
        position.x--;
        if (position.x == 0)
        {
            die();
            return;
        }
        show();
        DynamicWordArray.removeWordsAtPosition(position);
    }

    private void show(){
        PrimitiveType.Console.moveTo(position.x, position.y);
        PrimitiveType.Console.setBgColor(Color.WHITE);
        System.out.print("                                                                                ");
        PrimitiveType.Console.resetStyle();
    }

    private void selfClear(){
        PrimitiveType.Console.moveTo(position.x, position.y);
        System.out.print("                                                                                ");
    }

    private void die(){
        PrimitiveType.sW = null;
    }

}