import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Croissant {
    static  int numberOfCroissant;
    static LinkedList<Croissant> CroissantList = new LinkedList<>();
    static Random rand = new Random();
    int xCroissant, yCroissant;

    Croissant(int x, int y) {
        xCroissant = x;
        yCroissant= y;
        numberOfCroissant = CroissantList.size();
    }

    public static void resetCroissantList(){
        if(CroissantList.isEmpty() == false && CroissantList != null){
            for(int i = 0; i<CroissantList.size(); i++) {
                Croissant temp = CroissantList.get(i);
                CroissantList.remove(temp);
            }
        }
    }

    public static void generateCroissantList(int nr) {
        for (int i = 0; i < nr; i++) {
            CroissantList.add(new Croissant(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(3*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }


    public static void draw(Graphics g) {
        for (Croissant cro :CroissantList) {
            g.drawImage(main.mancare6, cro.xCroissant, cro.yCroissant, null);}
    }

    public static void move(){
        for(Croissant cro : CroissantList){
            cro.setyCroissant();
        }
        for(int i=0; i<CroissantList.size(); i++)
        {

            Croissant temp = CroissantList.get(i);
            if(temp.yCroissant>=GamePanel.GAME_HEIGHT) {
                CroissantList.remove(temp);
                numberOfCroissant--;
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

    private void setyCroissant() {

        if(GamePanel.niv==0)
            yCroissant +=2;
        else
            yCroissant+=3;
    }

    public static void checkCollision(){
        for (int i = 0; i < CroissantList.size(); i++) {
            Croissant temp = CroissantList.get(i);
            if (temp.xCroissant+60 >= Cat.xCat && temp.yCroissant+60>=Cat.yCat && temp.xCroissant <= Cat.xCat+60) {
                CroissantList.remove(temp);
                numberOfCroissant--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


}
