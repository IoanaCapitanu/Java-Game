import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Cake {
    static  int numberOfcake;
    static LinkedList<Cake> cakeList = new LinkedList<>();
    static Random rand = new Random();
    int xCake, yCake;

    Cake(int x, int y) {
        xCake = x;
        yCake = y;
        numberOfcake = cakeList.size();
    }

    public static void resetCakeList(){
        if(cakeList.isEmpty() == false && cakeList != null){
            for(int i = 0; i<cakeList.size(); i++) {
                Cake temp = cakeList.get(i);
                cakeList.remove(temp);
            }
        }
    }

    public static void generatecakeList(int nr) {
        for (int i = 0; i < nr; i++) {
            cakeList.add(new Cake(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    public static void draw(Graphics g) {
        for (Cake c : cakeList) {
            g.drawImage(main.mancare9, c.xCake, c.yCake, null);}

    }
    public static void move(){
        for(Cake c : cakeList){
            c.setYcake();
        }
        for(int i=0; i<cakeList.size(); i++)
        {

            Cake temp = cakeList.get(i);
            if(temp.yCake>=GamePanel.GAME_HEIGHT) {
                cakeList.remove(temp);
                numberOfcake--;
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
        for (int i = 0; i < cakeList.size(); i++) {
            Cake temp = cakeList.get(i);
            if (temp.xCake+60 >= Cat.xCat && temp.yCake+60>=Cat.yCat && temp.xCake <= Cat.xCat+60) {
                cakeList.remove(temp);
                numberOfcake--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


    public void setYcake() {
        if(GamePanel.niv==0)
            yCake +=2;
        else
            yCake+=3;}
}
