import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Steak {
    static  int numberOfSteak;
    static LinkedList<Steak> SteakList = new LinkedList<>();
    static Random rand = new Random();
    int xSteak, ySteak;

    Steak(int x, int y) {
        xSteak = x;
        ySteak = y;
        numberOfSteak = SteakList.size();
    }

    public static void resetSteakList(){
        if(SteakList.isEmpty() == false && SteakList != null){
            for(int i = 0; i<SteakList.size(); i++) {
                Steak temp = SteakList.get(i);
                SteakList.remove(temp);
            }
        }

    }

    public static void generateSteakList(int nr) {
        for (int i = 0; i < nr; i++) {
            SteakList.add(new Steak(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }


    public static void draw(Graphics g) {
        for (Steak steak :SteakList) {
            g.drawImage(main.mancare4, steak.xSteak, steak.ySteak, null);}
    }
    public static void move(){
        for(Steak steak : SteakList){
            steak.setySteak();
        }
        for(int i=0; i<SteakList.size(); i++)
        {

            Steak temp = SteakList.get(i);
            if(temp.ySteak>=GamePanel.GAME_HEIGHT) {
                SteakList.remove(temp);
                numberOfSteak--;
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

    private void setySteak() {

        if(GamePanel.niv==0)
            ySteak +=2;
        else
            ySteak+=3;
    }

    public static void checkCollision(){
        for (int i = 0; i < SteakList.size(); i++) {
            Steak temp = SteakList.get(i);
            if (temp.xSteak+60 >= Cat.xCat && temp.ySteak+60>=Cat.yCat && temp.xSteak <= Cat.xCat+60) {
                SteakList.remove(temp);
                numberOfSteak--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


}
