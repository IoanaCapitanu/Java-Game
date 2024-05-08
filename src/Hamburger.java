import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Hamburger {
    static  int numberOfHamburgers;
    static LinkedList<Hamburger> HamburgerList = new LinkedList<>();
    static Random rand = new Random();
    int xHamburger, yHamburger;



    Hamburger(int x, int y) {
        xHamburger = x;
        yHamburger = y;
        numberOfHamburgers = HamburgerList.size();
    }

    public static void resetHamburgerList(){
        if(HamburgerList.isEmpty() == false && HamburgerList != null){
            for(int i = 0; i<HamburgerList.size(); i++) {
                Hamburger temp = HamburgerList.get(i);
                HamburgerList.remove(temp);
            }
        }
    }

    public static void generateHamburgerList(int nr) {
        for (int i = 0; i < nr; i++) {
            HamburgerList.add(new Hamburger(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(3*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }


    public static void draw(Graphics g) {
        for (Hamburger hamburger :HamburgerList) {
            g.drawImage(main.mancare2, hamburger.xHamburger, hamburger.yHamburger, null);}
    }
    public static void move(){
        for(Hamburger hamburger : HamburgerList){
            hamburger.setYHamburger();
        }
        for(int i=0; i<HamburgerList.size(); i++)
        {
            Hamburger temp = HamburgerList.get(i);
            if(temp.yHamburger>=GamePanel.GAME_HEIGHT) {
                HamburgerList.remove(temp);
                numberOfHamburgers--;
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

    private void setYHamburger() {

        if(GamePanel.niv==0)
            yHamburger +=2;
        else
            yHamburger+=3;
    }

    public static void checkCollision() {
        for (int i = 0; i < HamburgerList.size(); i++) {
            Hamburger temp = HamburgerList.get(i);
            if (temp.xHamburger + 60 >= Cat.xCat && temp.yHamburger + 60 >= Cat.yCat && temp.xHamburger <= Cat.xCat + 60) {
                HamburgerList.remove(temp);
                numberOfHamburgers--;
                Cat.a = 1;
                Cat.contor = 0;
            }
        }
    }
}
