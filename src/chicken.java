import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class chicken {
    static  int numberOfchicken;
    static LinkedList<chicken> chickenList = new LinkedList<>();
    static Random rand = new Random();
    int xchicken, ychicken;

    chicken(int x, int y) {
        xchicken = x;
        ychicken = y;
        numberOfchicken = chickenList.size();
    }

    public static void resetChickenList(){
        if(chickenList.isEmpty() == false && chickenList != null){
            for(int i = 0; i<chickenList.size(); i++) {
                chicken temp = chickenList.get(i);
                chickenList.remove(temp);
            }
        }
    }


    public static void generateChickenList(int nr) {
        for (int i = 0; i < nr; i++) {
            chickenList.add(new chicken(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    public static void draw(Graphics g) {
        for (chicken c : chickenList) {
            g.drawImage(main.mancare1, c.xchicken, c.ychicken, null);}

    }
    public static void move(){
        for(chicken c : chickenList){
            c.setYEnemy();
        }
        for(int i=0; i<chickenList.size(); i++)
        {

            chicken temp = chickenList.get(i);
            if(temp.ychicken>=GamePanel.GAME_HEIGHT) {
                chickenList.remove(temp);
                numberOfchicken--;
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
        for (int i = 0; i < chickenList.size(); i++) {
            chicken temp = chickenList.get(i);
            if (temp.xchicken+60 >= Cat.xCat && temp.ychicken+60>=Cat.yCat && temp.xchicken <= Cat.xCat+60) {
                chickenList.remove(temp);
                numberOfchicken--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


    public void setYEnemy() {
        if(GamePanel.niv==0)
          ychicken +=2;
    else
        ychicken+=3;}
}
