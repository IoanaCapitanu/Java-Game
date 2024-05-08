import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class icecream {
    static  int numberOficecream;
    static LinkedList<icecream> icecreamList = new LinkedList<>();
    static Random rand = new Random();
    int xicecream, yicecream;

    icecream(int x, int y) {
        xicecream = x;
        yicecream = y;
        numberOficecream = icecreamList.size();
    }

    public static void reseticecreamList(){
        if(icecreamList.isEmpty() == false && icecreamList != null){
            for(int i = 0; i<icecreamList.size(); i++) {
                icecream temp = icecreamList.get(i);
                icecreamList.remove(temp);
            }
        }
    }

    public static void generateicecreamList(int nr) {
        for (int i = 0; i < nr; i++) {
            icecreamList.add(new icecream(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    public static void draw(Graphics g) {
        for (icecream c : icecreamList) {
            g.drawImage(main.mancare8, c.xicecream, c.yicecream, null);}

    }
    public static void move(){
        for(icecream c : icecreamList){
            c.setYicecream();
        }
        for(int i=0; i<icecreamList.size(); i++)
        {

            icecream temp = icecreamList.get(i);
            if(temp.yicecream>=GamePanel.GAME_HEIGHT) {
                icecreamList.remove(temp);
                numberOficecream--;
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
        for (int i = 0; i < icecreamList.size(); i++) {
            icecream temp = icecreamList.get(i);
            if (temp.xicecream+60 >= Cat.xCat && temp.yicecream+60>=Cat.yCat && temp.xicecream <= Cat.xCat+60) {
                icecreamList.remove(temp);
                numberOficecream--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


    public void setYicecream() {
        if(GamePanel.niv==0)
            yicecream +=2;
        else
            yicecream+=3;
    }
}
