import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class taco {
    static  int numberOftaco;
    static LinkedList<taco> tacoList = new LinkedList<>();
    static Random rand = new Random();
    int xtaco, ytaco;

    taco(int x, int y) {
        xtaco = x;
        ytaco = y;
        numberOftaco = tacoList.size();
    }

    public static void resettacoList(){
        if(tacoList.isEmpty() == false && tacoList != null){
            for(int i = 0; i<tacoList.size(); i++) {
                taco temp = tacoList.get(i);
                tacoList.remove(temp);
            }
        }
    }

    public static void generateTacoList(int nr) {
        for (int i = 0; i < nr; i++) {
            tacoList.add(new taco(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    public static void draw(Graphics g) {
        for (taco c : tacoList) {
            g.drawImage(main.mancare1, c.xtaco, c.ytaco, null);}

    }
    public static void move(){
        for(taco c : tacoList){
            c.setYtaco();
        }
        for(int i=0; i<tacoList.size(); i++)
        {

            taco temp = tacoList.get(i);
            if(temp.ytaco>=GamePanel.GAME_HEIGHT) {
                tacoList.remove(temp);
                numberOftaco--;
                if(Heart.nr>1)
                {
                    Heart.nr--;
                    Heart.a=1;
                }
                else {
                    GamePanel.state = 2;
                }
            }


        }
    }

    public static void checkCollision(){
        for (int i = 0; i < tacoList.size(); i++) {
            taco temp = tacoList.get(i);
            if (temp.xtaco+60 >= Cat.xCat && temp.ytaco+60>=Cat.yCat && temp.xtaco <= Cat.xCat+60) {
                tacoList.remove(temp);
                numberOftaco--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


    public void setYtaco() {
        if(GamePanel.niv==0)
            ytaco +=2;
        else
            ytaco+=3;
    }
}
