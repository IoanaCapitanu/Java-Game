import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/*
Clasa publica Cat - obiectul personaj
 */
public class Cat extends JComponent {
    private static Cat single_instance = null;

    public static Cat getInstance( int x, int y)
    {
        if(single_instance == null)
            single_instance=new Cat ( x,y);
        return single_instance;

    }
    int xVelocity; //
    int yVelocity;
    int speed = 20; // viteza de miscare a personajului
    public static int yCat; // coordonate obiect
    public static int xCat;
    public static int a,contor=0; // variabile folosite pentru a schimba imaginea pisicii


    //constructor de initializare al clasei cat
    Cat(int x, int y) {
        xCat = x;
        yCat = y;
    }

    /*
    Functie keyPressed(KeyEvent e) - folosita pentru gestiunea butoanelor
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) { // in momentul apasarii tastei A- pisica se misca in partea stanga
            setXDirection(-speed);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) { // in momentul apasarii tastei D- pisica se misca in partea dreapta
            setXDirection(speed);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            a=2;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            setXDirection(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            setXDirection(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
           a=0;
        }
    }

    /*
    functia setXDirection(int xDirection) este functie care seteaza  directia in care merge pisica, primeste ca parametru o variabila de tip int ce reprezinta
     */
    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }

    /*
    Functia move() reprezinta functia de miscare a obiectului de tip cat
     */
    public void move() {
        xCat = xCat + xVelocity;
        yCat = yCat + yVelocity;

    }
    public int getxCat(){return xCat;} // functie ce returneaza coordonata x a obiectului cat

    public void setxCat(int coord){xCat = coord;}

    /*
    Functia draw() functie care deseneaza obiectul de tip cat
    /cu ajutorul variabilelor a si contor schimb imaginea psicii in momentul coleziunii cu hrana care este generata random
     */
    public void draw(Graphics g) {
        if(a==1)
            if(contor<20)
            {
                {g.drawImage(main.pisica2, xCat, yCat, null);
                contor++;}

            }else {
                a=0;
            }
        else if (a==2)
        {
                g.drawImage(main.pisica3, xCat, yCat, null);
        }
        else
        {
            g.drawImage(main.pisica, xCat, yCat, null);
        }
    }
    }
