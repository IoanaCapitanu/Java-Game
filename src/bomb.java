import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/*
*Clasa publica bomba
 */
public class bomb {
    static  int numberOfbomb; // numarul de bombe
    static LinkedList<bomb> bombList = new LinkedList<>(); // lista care v-a contine numarul de obiecte de tip bomba
    static Random rand = new Random();
    int xbomb, ybomb; // coordonate obiect


    /*
    Constructor de initializare a clasei bomba
     */
    bomb(int x, int y) {
        xbomb = x;
        ybomb = y;
        numberOfbomb = bombList.size();
    }

    /*
    Functie care genereaza random un numar de bombe
    - primeste ca parametru numarul de bombe
     */

    public static void resetbombList(){
        if(bombList.isEmpty() == false && bombList != null){
            for(int i = 0; i<bombList.size(); i++) {
                bomb temp = bombList.get(i);
                bombList.remove(temp);
            }
        }
    }
    public static void generatebombList(int nr) {
        for (int i = 0; i < nr; i++) {
            bombList.add(new bomb(rand.nextInt(GamePanel.GAME_WIDTH-60) , (-1)*rand.nextInt(1*GamePanel.GAME_HEIGHT)-GamePanel.GAME_HEIGHT));
        }
    }

    /*
     functia draw(graphics g ) -functie care deseneaza obiectul bomba
     */
    public static void draw(Graphics g) {
        for (bomb c : bombList) { // parcurgem lista si desenam fiecare obiect de tip bomba
            g.drawImage(main.bomb, c.xbomb, c.ybomb, null);}

    }
    //functia move() - functie de miscare a obiectului
    public static void move(){
        for(bomb c : bombList){
            c.setYbomb(); // setYbomb() - functie care defineste viteza de miscare a obiectului
        }
        for(int i=0; i<bombList.size(); i++)
        {

            bomb temp = bombList.get(i);
            if(temp.ybomb>=GamePanel.GAME_HEIGHT) { // daca obiectul depaseste perimetrul jocului, este eliminat
                bombList.remove(temp);
                numberOfbomb--;// scad numarul de bombe
            }


        }
    }

    /*
      functia checkCollision() - functie care verifica coleziunile obiectului bomba cu cu pisica
     */

    public static void checkCollision(){
        for (int i = 0; i < bombList.size(); i++) { //parcurgem lista de obiecte
            bomb temp = bombList.get(i);
            if (temp.xbomb+60 >= Cat.xCat && temp.ybomb+60>=Cat.yCat && temp.xbomb <= Cat.xCat+60) { // cand are loc coleziunea cu pisica eliminam obiectul de tip bomba
                bombList.remove(temp);
                numberOfbomb--;
                /*
                Cat.a=1
                Aceasta asignare ajuta la animatia pisicii. In momentul coleziunii se schimba imaginea obiectului de tip pisica
                 */
                Cat.a=1;
                Cat.contor=0; // setam variabila contor cu 0, in momentul coleziunii se schimba imaginea obiectului de tip pisica pentru un interval de timp
                /*
                Instructiunea if (Heart.nr >1) verifica daca numarul de vieti este mai mare decat 1
                In momentul coleziunii pisicii cu bomba, numarul de vieti scade
                 */
                if(Heart.nr>1)
                {
                    Heart.nr--;
                    Heart.a=1;
                }
                else {
                    GamePanel.state = 2; // daca numarul de vietii e 0, se intra in starea 2, cea cu GAME OVER
                }

            }
        }
    }


    public void setYbomb() {ybomb +=3;} // functie care defineste viteza obiectului de tip bomba
}
