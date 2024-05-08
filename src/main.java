
import java.awt.*;


public class main {
    static public Image pisica;
    static public Image pisica2;
    static public Image pisica3;

    static public Image mancare1;
    static public Image mancare2;
    static public Image mancare3;
    static public Image mancare4;
    static public Image mancare5;
    static public Image mancare6;
    static public Image mancare7;
    static public Image mancare8;
    static public Image mancare9;
    static public Image mancare10;

    static public Image heart;
    static public Image bomb;

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

        //70x70a
        pisica = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\finalcat1.png");
        pisica2 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\openmouthcat.png");
        pisica3 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\catShield.png");

        mancare1 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\chicken.png");
        mancare2 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\croissant.png");
        mancare3 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\sushi.png");
        mancare4 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\steak.png");
        mancare5 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\strawberry.png");
        mancare6 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\hamburger.png");
        mancare7 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\taco.png");
        mancare8 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\icecream.png");
        mancare9 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\donut.png");
        mancare10 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\cake.png");
        bomb = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\Food\\bomb.png");



        heart = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\heart.png");


    }
}
