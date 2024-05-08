import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Sushi {
    static  int numberOfSushi;
    static LinkedList<Sushi> SushiList = new LinkedList<>();
    static Random rand = new Random();
    int xSushi, ySushi;

    Sushi(int x, int y) {
        xSushi = x;
        ySushi = y;
        numberOfSushi = SushiList.size();
    }

    public static void resetSushiList(){
        if(SushiList.isEmpty() == false && SushiList != null){
            for(int i = 0; i<SushiList.size(); i++) {
                Sushi temp = SushiList.get(i);
                SushiList.remove(temp);
            }
        }
    }

    public static void generateSushiList(int nr) {
        for (int i = 0; i < nr; i++) {
            SushiList.add(new Sushi(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(3*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }


    public static void draw(Graphics g) {
        for (Sushi sushi :SushiList) {
            g.drawImage(main.mancare3, sushi.xSushi, sushi.ySushi, null);}
    }
    public static void move(){
        for(Sushi sushi : SushiList){
            sushi.setySushi();
        }
        for(int i=0; i<SushiList.size(); i++)
        {

            Sushi temp = SushiList.get(i);
            if(temp.ySushi>=GamePanel.GAME_HEIGHT) {
                SushiList.remove(temp);
                numberOfSushi--;
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

    private void setySushi() {

        if(GamePanel.niv==0)
            ySushi +=2;
        else
            ySushi+=3;
    }

    public static void checkCollision(){
        for (int i = 0; i < SushiList.size(); i++) {
            Sushi temp = SushiList.get(i);
            if (temp.xSushi+60 >= Cat.xCat && temp.ySushi+60>=Cat.yCat && temp.xSushi <= Cat.xCat+60) {
                SushiList.remove(temp);
                numberOfSushi--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }

}
