
import javax.swing.*;
import java.awt.*;

/*
clasa publica Heart - obiect ce reprezinta viata personajului
 */
public class Heart {
    static int a=0; //
    static int nr=4;

    public static void draw(Graphics g) { //functie care deseneaza 4 inimi ce reprezinta numarul de vieti ale jucatorului
        for (int i = 0; i < nr; i++) {
            g.drawImage(main.heart, 530, 0, null);
        }
            g.setColor(new Color(150, 45, 35));
            g.setFont(new Font("Bookman Old Style", Font.BOLD, 40));
            g.drawString("4/", 600, 52);
            g.drawString(String.valueOf(nr), 650, 52);

            if (a == 1) {
                g.drawString("4/", 600, 52); // desenez 4 - numarul de vieti
                g.drawString(String.valueOf(nr), 650, 52);
            }

        }
    }