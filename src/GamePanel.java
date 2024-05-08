import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;


public class GamePanel extends JPanel implements Runnable {
    Connection c = null;
    Statement stmt = null;


    int fromGame = 0;
    public int highlighted = 0;//0-start game, 1-about, 2-load game, 3-exit
    public int contor = 0;
    static int state = -1;
    double bgX1 = 0;
    Thread gameThread;
    Cat Cat;
    Image image;
    Graphics graphics;
    static int GAME_WIDTH = 720;
    static int GAME_HEIGHT = 635;
    final Dimension screen_size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static int niv = 1;    //variabila de care ma folosesc sa schimb viteza alimentelor in functie de nivel;

    GamePanel() {
        this.addKeyListener(new AL());
        this.setFocusable(true);
        this.setPreferredSize(screen_size);
        gameThread = new Thread(this);
        gameThread.start();
        generateEnvironment();
    }

    public void resetEnvironment() {
        Heart.a = 0;
        Heart.nr = 4;
        chicken.resetChickenList();
        Cake.resetCakeList();
        Donut.resetDonutList();
        taco.resettacoList();
        icecream.reseticecreamList();
        Hamburger.resetHamburgerList();
        Sushi.resetSushiList();
        Steak.resetSteakList();
        strawberry.resetstrawberryList();
        Croissant.resetCroissantList();
        if(niv > 1){
            bomb.resetbombList();
        }
    }

    public void generateEnvironment() {
        Heart.a = 0;
        Heart.nr = 4;
        chicken.generateChickenList(3);
        chicken.numberOfchicken = chicken.chickenList.size();
        Cake.generatecakeList(3);
        Cake.numberOfcake = Cake.cakeList.size();
        Donut.generatedonutList(3);
        Donut.numberOfdonut = Donut.donutList.size();
        taco.generateTacoList(3);
        taco.numberOftaco = taco.tacoList.size();
        icecream.generateicecreamList(3);
        icecream.numberOficecream = icecream.icecreamList.size();
        Hamburger.generateHamburgerList(3);
        Hamburger.numberOfHamburgers = Hamburger.HamburgerList.size();
        Sushi.generateSushiList(3);
        Sushi.numberOfSushi = Sushi.SushiList.size();
        Steak.generateSteakList(3);
        Steak.numberOfSteak = Steak.SteakList.size();
        strawberry.generatestrawberryList(3);
        strawberry.numberOfstrawberry = strawberry.strawberryList.size();
        Croissant.generateCroissantList(3);
        Croissant.numberOfCroissant = Croissant.CroissantList.size();
        if(niv > 1){
            bomb.generatebombList(4);
            bomb.numberOfbomb = 4;

        }
        Cat = Cat.getInstance(0, GAME_HEIGHT - 100);


    }

    public void paint(Graphics g) {
        super.paintComponent(g);
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {

        super.paintComponent(g);
        if (state == 0) {
            //in game
            if (niv == 1) {
                Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\fundal.jpg");
                g.drawImage(menuBg, 0, 0, null);
            } else if (niv == 2) {
                Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\fundal2.png");
                g.drawImage(menuBg, 0, 0, null);
            }
            Image menuBg1 = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\pause.png");
            g.drawImage(menuBg1, 0, 0, null);


            drawComponents(g);
            if (contor < 200) {
                Image sageatas = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\sageatas.png");
                g.drawImage(sageatas, 30, 540, null);
                Image sageata = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\poze\\sageata.png");
                g.drawImage(sageata, 600, 550, null);
                contor++;
            }

        } else if (state == 1) {
            //level complete
            Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\level1.jpg");
            g.drawImage(menuBg, 0, 0, null);

        } else if (state == 2) {
            //game over
            Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\gameover.jpg");
            g.drawImage(menuBg, 0, 0, null);
        } else if (state == 3) {
            //about menu
            Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\fundal3.png");
            g.drawImage(menuBg, 0, 0, null);
        } else if (state == 4) {
            //game complete
            Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\FINAL.jpg");
            g.drawImage(menuBg, 0, 0, null);
        } else if (state == -1) {
            Image menuBg = Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\backgrounds\\pisicuta.jpg");
            g.drawImage(menuBg, 0, 0, null);
            g.setColor(new Color(134, 86, 51));
            g.setFont(new Font("Bookman Old Style", Font.BOLD, 50));
            g.drawString("HUNGRY CAT", 190, 100);
            if (highlighted == 0)
                g.setColor(new Color(66, 18, 12));
            if (fromGame == 0)
                g.drawString("Start", 560, 550);
            else if (fromGame == 2)
                g.drawString("Start", 560, 550);
            else
                g.drawString("Continue", 470, 570);

            g.setColor(new Color(134, 86, 51));
            if (highlighted == 1)
                g.setColor(new Color(66, 18, 12));
            g.drawString("About", 280, 450);

            g.setColor(new Color(134, 86, 51));
            if (highlighted == 2)
                g.setColor(new Color(66, 18, 12));
            g.drawString("Load game ", 250, 500);

            g.setColor(new Color(134, 86, 51));
            if (highlighted == 3)
                g.setColor(new Color(66, 18, 12));
            g.drawString("Exit ", 290, 550);

        }
    }

    public void move() {
        Cat.move();
        Hamburger.move();
        Sushi.move();
        Steak.move();
        strawberry.move();
        Croissant.move();
        chicken.move();
        taco.move();
        icecream.move();
        Cake.move();
        Donut.move();
        bomb.move();

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                bgX1 = bgX1 - 5;
                if (bgX1 == -1280) {
                    bgX1 = 0;
                    repaint();
                }

                if (chicken.numberOfchicken <= 0 && Hamburger.numberOfHamburgers <= 0 && Croissant.numberOfCroissant <= 0 && Steak.numberOfSteak <= 0 && strawberry.numberOfstrawberry <= 0 && Sushi.numberOfSushi <= 0 && taco.numberOftaco <= 0 && icecream.numberOficecream <= 0 && Cake.numberOfcake <= 0 && Donut.numberOfdonut <= 0) {
                    {
                        if (niv == 2)
                            state = 4;
                        else state = 1;
                    }

                    chicken.generateChickenList(2);
                    chicken.numberOfchicken = 2;
                    Hamburger.generateHamburgerList(2);
                    Hamburger.numberOfHamburgers = 2;
                    Croissant.generateCroissantList(2);
                    Croissant.numberOfCroissant = 2;
                    Steak.generateSteakList(2);
                    Steak.numberOfSteak = 2;
                    strawberry.generatestrawberryList(2);
                    strawberry.numberOfstrawberry = 2;
                    Sushi.generateSushiList(2);
                    Sushi.numberOfSushi = 2;
                    taco.generateTacoList(2);
                    taco.numberOftaco = 2;
                    icecream.generateicecreamList(2);
                    icecream.numberOficecream = 2;
                    Cake.generatecakeList(2);
                    Cake.numberOfcake = 2;
                    Donut.generatedonutList(2);
                    Donut.numberOfdonut = 2;
                    bomb.generatebombList(4);
                    bomb.numberOfbomb = 4;

                }
                if (state == 0) {
                    move();
                    checkCollision();
                }
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            Cat.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            Cat.keyReleased(e);
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (highlighted == 0)
                    highlighted = 3;
                else highlighted--;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (highlighted == 3)
                    highlighted = 0;
                else highlighted++;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (state == 4) {
                    resetEnvironment();
                    generateEnvironment();
                    niv = 1;
                    state = -1;
                } else if (state == 2) {
                    niv = 1;
                    resetEnvironment();
                    generateEnvironment();
                    state = -1;
                } else if (state == 1) {
                    if (niv == 3) {
                        niv = 1;
                        File file = new File("savegame.db");
                        if (file.exists())
                            file.delete();
                        state = 4;
                    } else {
                        niv++;
                        resetEnvironment();
                        try {
                            File file = new File("savegame.db");
                            if (file.exists())
                                file.delete();
                            Class.forName("org.sqlite.JDBC");
                            c = DriverManager.getConnection("jdbc:sqlite:savegame.db");
                            stmt = c.createStatement();
                            String sql = "CREATE TABLE SAVEGAME " +
                                    "(ID INT PRIMARY KEY NOT NULL," +
                                    " LEVEL INT NOT NULL, " +
                                    " HEARTS INT NOT NULL " +
                                    " )";
                            stmt.execute(sql);
                            sql = "INSERT INTO SAVEGAME (ID,LEVEL,HEARTS) VALUES (1," + niv + "," + Heart.a + ")";
                            stmt.execute(sql);
                            stmt.close();
                            c.close();
                        } catch (Exception f) {
                            System.err.println(f.getClass().getName() + ": " + f.getMessage());
                            System.exit(0);
                        }
                        generateEnvironment();

                        state = 0;
                        repaint();
                    }
                } else if (state != 0 && state != 1 && state != 2 && state != 3 && state != 4) {
                    switch (highlighted) {
                        case 0:
                            state = 0;
                            break;
                        case 1:
                            state = 3;
                            break;
                        case 2:
                            File file = new File("savegame.db");
                            if (file.exists()) {
                                try {
                                    Class.forName("org.sqlite.JDBC");
                                    c = DriverManager.getConnection("jdbc:sqlite:savegame.db");
                                    stmt = c.createStatement();
                                    ResultSet rs = stmt.executeQuery("SELECT * FROM SAVEGAME;");
                                    while (rs.next()) {
                                        niv = rs.getInt("LEVEL");
                                        Heart.a = rs.getInt("HEARTS");
                                    }
                                    rs.close();
                                    stmt.close();
                                    c.close();
                                } catch (Exception f) {
                                    System.out.println("Eroare la citirea din baza de date");
                                }
                            }

                            resetEnvironment();
                            generateEnvironment();
                            state = 0;
                            break;
                        case 3:
                            System.exit(0);
                        default:
                            break;
                    }
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                state = -1;
                fromGame = 1;
                highlighted = 0;
                if (state == 2 || state == 3)
                    fromGame = 2;
            }


        }
    }

    public void checkCollision() {
        if (Cat.getxCat() <= -10)
            Cat.setxCat(0);
        if (Cat.getxCat() >= GAME_WIDTH - 100)
            Cat.setxCat(GAME_WIDTH - 100);


        Hamburger.checkCollision();
        Sushi.checkCollision();
        Steak.checkCollision();
        strawberry.checkCollision();
        Croissant.checkCollision();
        chicken.checkCollision();
        taco.checkCollision();
        icecream.checkCollision();
        Donut.checkCollision();
        Cake.checkCollision();
        bomb.checkCollision();

    }


    public void drawComponents(Graphics g) {
        Cat.draw(g);
        Hamburger.draw(g);
        Heart.draw(g);
        Sushi.draw(g);
        Steak.draw(g);
        strawberry.draw(g);
        Croissant.draw(g);
        chicken.draw(g);
        taco.draw(g);
        icecream.draw(g);
        Cake.draw(g);
        Donut.draw(g);
        bomb.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }
}
