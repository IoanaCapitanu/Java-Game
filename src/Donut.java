import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Donut {
    static  int numberOfdonut;
    static LinkedList<Donut> donutList = new LinkedList<>();
    static Random rand = new Random();
    int xdonut, ydonut;

    Donut(int x, int y) {
        xdonut = x;
        ydonut = y;
        numberOfdonut = donutList.size();
    }

    public static void resetDonutList(){
        if(donutList.isEmpty() == false && donutList != null){
            for(int i = 0; i<donutList.size(); i++) {
                Donut temp = donutList.get(i);
                donutList.remove(temp);
            }
        }
    }

    public static void generatedonutList(int nr) {
        for (int i = 0; i < nr; i++) {
            donutList.add(new Donut(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    public static void draw(Graphics g) {
        for (Donut c : donutList) {
            g.drawImage(main.mancare10, c.xdonut, c.ydonut, null);}

    }
    public static void move(){
        for(Donut c : donutList){
            c.setYdonut();
        }
        for(int i=0; i<donutList.size(); i++)
        {

            Donut temp = donutList.get(i);
            if(temp.ydonut>=GamePanel.GAME_HEIGHT) {
                donutList.remove(temp);
                numberOfdonut--;
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
        for (int i = 0; i < donutList.size(); i++) {
            Donut temp = donutList.get(i);
            if (temp.xdonut+60 >= Cat.xCat && temp.ydonut+60>=Cat.yCat && temp.xdonut<= Cat.xCat+60) {
                donutList.remove(temp);
                numberOfdonut--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


    public void setYdonut() {
        if(GamePanel.niv==0)
            ydonut +=2;
        else
            ydonut+=3;
    }
}
