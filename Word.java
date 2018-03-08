import com.codecool.termlib.Terminal;
import com.codecool.termlib.Coord;
import com.codecool.termlib.Direction;
import com.codecool.termlib.Color;
import java.lang.Math;

public class Word {

    public static String[] nameList = {
        "fold",
        "pizzas",
        "long-term",
        "wooden",
        "dynamic",
        "zoom",
        "hate",
        "kindhearted",
        "count",
        "bounce",
        "acidic",
        "crate",
        "heap",
        "fearful",
        "sound",
        "wry",
        "store",
        "chop",
        "avoid",
        "heartbreaking",
        "busy",
        "fixed",
        "painful",
        "stale",
        "love",
        "drab",
        "lopsided",
        "burn",
        "zipper",
        "bucket",
        "eminent",
        "train",
        "eyes",
        "pie",
        "feeling",
        "unruly",
        "crayon",
        "intelligent",
        "boundless",
        "old-fashioned",
        "impulse",
        "beautiful",
        "metal",
        "snail",
        "cure",
        "substantial",
        "opposite",
        "head",
        "walk",
        "dark",
        "use",
        "whip",
        "long",
        "preach",
        "satisfy",
        "famous",
        "talented",
        "loss",
        "wing",
        "wacky",
        "fluttering",
        "meek",
        "wander",
        "geese",
        "line",
        "tangible",
        "unequaled",
        "fierce",
        "subsequent",
        "tangy",
        "history",
        "ludicrous",
        "wise",
        "rapid",
        "false",
        "psychotic",
        "brawny",
        "flowers",
        "children",
        "sprout",
        "detect",
        "offbeat",
        "twig",
        "acid",
        "didactic",
        "duck",
        "moor",
        "shiver",
        "giddy",
        "protective",
        "vagabond",
        "thirsty",
        "gainful",
        "girl",
        "envious",
        "robust",
        "bury",
        "bomb",
        "anger",
        "introduce"};

    public String name;
    public Coord position;
    public int hitCount;
    public Boolean destroyed = false;
    public Word(int x, int y, String name)
    {
        hitCount = 0;
        this.name = name;
        position = new Coord();
        position.x = x;
        position.y = y;
        show();
    }

    public Hitvalue wordHitHandler(char typedChar) {
        if (name.charAt(hitCount) == typedChar){
            hitCount ++;
            if (hitCount == name.length())
            {
                die();
                return Hitvalue.DESTROYED;
            }
            else {
                refresh();
                return Hitvalue.HIT;
            }
        }
        return Hitvalue.MISS;
    }

    public void die(){
        selfClear();
        destroyed = true;
        DynamicWordArray.removeWord(this);
    }

    public void refresh() {
        selfClear();
        show();
    }

    public void move() {
        selfClear();
        int[] direction = setMoveDirection();
        position.x += direction[0];
        position.y += direction[1];
        if (!checkPlayerHit()){
            show();
        }
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

    public void selfClear() {
        PrimitiveType.Console.moveTo(position.x, position.y);
        for (int i = 0; i < name.length(); i++) {
            System.out.print(" ");
        }
    }

    private int[] setMoveDirection(){
        int[] direction = new int[2];
        int xDir = PrimitiveType.player.position.x - this.position.x;
        int yDir = PrimitiveType.player.position.y - this.position.y;
        yDir = (int) Math.round(yDir / xDir);
        direction[0] = 1;
        direction[1] = yDir;
        return direction;
    }

    private boolean checkPlayerHit(){
        if (this.position.x == PrimitiveType.player.position.x - 2){
            PrimitiveType.player.takeDamage(1);
            die();
            return true;
        }
        return false;
    }

}