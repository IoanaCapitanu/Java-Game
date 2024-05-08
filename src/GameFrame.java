import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame
{
    GameFrame()
    {
        GamePanel gpl  = new GamePanel();
        this.add(gpl);
        this.setTitle("Hungry cat");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
