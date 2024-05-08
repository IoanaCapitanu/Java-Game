import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class strawberry{
    static  int numberOfstrawberry;
    static LinkedList<strawberry> strawberryList = new LinkedList<>();
    static Random rand = new Random();
    int xstrawberry, ystrawberry;

    strawberry(int x, int y) {
        xstrawberry = x;
        ystrawberry = y;
        numberOfstrawberry = strawberryList.size();
    }

    public static void resetstrawberryList(){
        if(strawberryList.isEmpty() == false && strawberryList != null){
            for(int i = 0; i<strawberryList.size(); i++) {
                strawberry temp = strawberryList.get(i);
                strawberryList.remove(temp);
            }
        }
    }

    public static void generatestrawberryList(int nr) {
        for (int i = 0; i < nr; i++) {
            strawberryList.add(new strawberry(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(3*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }


    public static void draw(Graphics g) {
        for (strawberry straw :strawberryList) {
            g.drawImage(main.mancare5, straw.xstrawberry, straw.ystrawberry, null);}
    }
    public static void move(){
        for(strawberry straw : strawberryList){
            straw.setystrawberry();
        }
        for(int i=0; i<strawberryList.size(); i++)
        {

            strawberry temp = strawberryList.get(i);
            if(temp.ystrawberry>=GamePanel.GAME_HEIGHT) {
                strawberryList.remove(temp);
                numberOfstrawberry--;
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

    private void setystrawberry() {
        if(GamePanel.niv==0)
            ystrawberry +=2;
        else
            ystrawberry+=3;
    }

    public static void checkCollision(){
        for (int i = 0; i < strawberryList.size(); i++) {
            strawberry temp = strawberryList.get(i);
            if (temp.xstrawberry+60 >= Cat.xCat && temp.ystrawberry+60>=Cat.yCat && temp.xstrawberry <= Cat.xCat+60) {
                strawberryList.remove(temp);
                numberOfstrawberry--;
                Cat.a=1;
                Cat.contor=0;
            }
        }
    }


}
