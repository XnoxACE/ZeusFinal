package zeus;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Zeus extends JFrame implements Runnable {

    // booleans for rooms that are used (main + 4 others)
    boolean room1;
    boolean room2;
    boolean room3;
    boolean room4;
    boolean room5;

    // all doors
    Door door1;
    Door door2;
    Door door3;
    Door door4;
    Door door5;
    Door door6;
    Door door7;
    Door door8;

    // all door x positions
    int door1XPos = 478;
    int door2XPos = 862;

    int door3XPos = 670;
    int door4XPos = 670;

    int door5XPos = 862;
    int door6XPos = 478;

    int door7XPos = 670;
    int door8XPos = 670;

    // all door y positions
    int door1YPos = 435;
    int door2YPos = 435;

    int door3YPos = 628;
    int door4YPos = 247;

    int door5YPos = 435;
    int door6YPos = 435;

    int door7YPos = 207;
    int door8YPos = 648;

    Image slide1;
    Image slidet;
    Image Scene;
    Image image;

    boolean animateFirstTime = true;
    boolean slide;
    boolean slide2;

    Graphics2D g;

    Player player;
    Npc bob;
    Npc bil;
    Npc jil;
    Npc kil;
    Npc mil;

    boolean titleScreen;
    boolean credits;

    static Zeus frame;

    public static void main(String[] args) {
        frame = new Zeus();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Zeus() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                    titleScreen = false;

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {

                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            
            public void keyPressed(KeyEvent e) {
                
                
                if (e.VK_W == e.getKeyCode()) {
                    player.move(0, 5);
                    player.isWalking = true;
                    player.walkingFoward = true;
                    player.walkingBack = false;
                    player.walkingRight = false;
                    player.walkingLeft = false;
                    
                }if (e.VK_S == e.getKeyCode()) {
                    player.move(0, -5);
                    player.isWalking = true;
                    player.walkingFoward = false;
                    player.walkingBack = true;
                    player.walkingRight = false;
                    player.walkingLeft = false;
                }if (e.VK_A == e.getKeyCode()) {
                    player.move(-5, 0);
                    player.isWalking = true;
                    player.walkingLeft = true;
                    player.walkingFoward = false;
                    player.walkingBack = false;
                    player.walkingRight = false;
                    
                }if (e.VK_D == e.getKeyCode()) {
                    player.move(5, 0);
                    player.isWalking = true;
                    player.walkingFoward = false;
                    player.walkingBack = false;
                    player.walkingRight = true;
                    player.walkingLeft = false;
                } else if (e.VK_E == e.getKeyCode()) {
                    slide = true;
                } else if (e.VK_F == e.getKeyCode()) {
                    slide2 = true;
                }
                else if (e.VK_X == e.getKeyCode()) {
                    slide = false;
                    slide2 = false;
                    credits = false;
                    
                }else if (e.VK_C == e.getKeyCode()) {
                   credits = true;
                    
                }
                

                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
    ////////////////////////////////////////////////////////////////////////////

    public void init() {
        requestFocus();
    }
    ////////////////////////////////////////////////////////////////////////////

    public void destroy() {}

    ////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        }
        //fill background

        int x[] = {
            Window.getX(0),
            Window.getX(Window.getWidth2()),
            Window.getX(Window.getWidth2()),
            Window.getX(0),
            Window.getX(0)
        };
        int y[] = {
            Window.getY(0),
            Window.getY(0),
            Window.getY(Window.getHeight2()),
            Window.getY(Window.getHeight2()),
            Window.getY(0)
        };
        //fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
        // draw border

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.drawImage(Scene, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        if(!titleScreen || credits)
            player.Draw(g, this);

        if (room1) {
            Scene = Toolkit.getDefaultToolkit().getImage("./ye.jpg");
            
        }

        if (room2) {
            Scene = Toolkit.getDefaultToolkit().getImage("./room2.jpg");
            
            bob.Draw(g, this);
            kil.Draw(g, this);
            slide1 = Toolkit.getDefaultToolkit().getImage("./2.jpg");
            slidet = Toolkit.getDefaultToolkit().getImage("./3.jpg");
            if (slide)
                g.drawImage(slide1, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
            if (slide2)
                g.drawImage(slidet, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        }
        if (room3) {
            Scene = Toolkit.getDefaultToolkit().getImage("./room5.jpg");
            
            bil.Draw(g, this);
            mil.Draw(g, this);
            slide1 = Toolkit.getDefaultToolkit().getImage("./4.jpg");
            slidet = Toolkit.getDefaultToolkit().getImage("./5.jpg");
            if (slide)
                g.drawImage(slide1, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
            if (slide2)
                g.drawImage(slidet, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        }
        if (room4) {
            Scene = Toolkit.getDefaultToolkit().getImage("./room3.jpg");
            
            jil.Draw(g, this);
            slide1 = Toolkit.getDefaultToolkit().getImage("./6.jpg");
            if (slide)
                g.drawImage(slide1, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        }
        /*if (room5) {
            Scene = Toolkit.getDefaultToolkit().getImage("./6.jpg");
            door8.Draw(g);
            bob.Draw(g, this);
            slide1 = Toolkit.getDefaultToolkit().getImage("./slide.jpg");
            if (slide)
                g.drawImage(slide1, Window.getX(0), Window.getY(0), Window.getWidth2(), Window.getHeight2(), this);
        }
*/
        if(titleScreen)
            Scene = Toolkit.getDefaultToolkit().getImage("./1.jpg");
        if(credits)
            Scene = Toolkit.getDefaultToolkit().getImage("./credits.jpg");
        gOld.drawImage(image, 0, 0, null);

    }

    ////////////////////////////////////////////////////////////////////////////aaddddd
    // needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.02; //time that 1 frame takes.
            int miliseconds = (int)(1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {}
        }
    }
    /////////////////////////////////////////////////////////////////////////

    public void reset() {

        room1 = true;
        room2 = false;
        room3 = false;
        room4 = false;
        room5 = false;
        room3 = false;
        slide = false;
        titleScreen = true;
        credits = false;
        player = new Player();
        bob = new Npc(500, 420);
        bil = new Npc(550, 510);
        jil = new Npc(800, 430);
        kil = new Npc(500, 510);
        mil = new Npc(800, 510);
        bob.faceL = true;
        bil.faceF = true;
        jil.faceR = true;
        door1 = new Door(door1XPos, door1YPos);
        door2 = new Door(door2XPos, door2YPos);
        door3 = new Door(door3XPos, door3YPos);
        door4 = new Door(door4XPos, door4YPos);
        door5 = new Door(door5XPos, door5YPos);
        door6 = new Door(door6XPos, door6YPos);
        door7 = new Door(door7XPos, door7YPos);
        door8 = new Door(door8XPos, door8YPos);
    }
    /////////////////////////////////////////////////////////////////////////

    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            //fmainRoomBackground = Toolkit.getDefaultToolkit().getImage("./testBackground.jpg");
            Npc.Init();
            Player.Init();
            reset();
        }
        player.animate();
        

        if (room1) {
            if (door1.Colide(player.xpos, player.ypos, frame) == true) {
                room2 = true;
                room1 = false;
                player.xpos = door2XPos - 25;

            }
            if (door3.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = false;
                room3 = true;
                player.ypos = door4YPos + 25;

            }
            if (door5.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = false;
                room4 = true;
                player.xpos = door6XPos + 25;

            }
            if (door7.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = false;
                room5 = true;
                player.ypos = door8YPos - 25;

            }
        }

        if (room2) {
            if (door2.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = true;
                room2 = false;
                player.xpos = door1XPos + 25;

            }

        }
        if (room3) {
            if (door4.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = true;
                room3 = false;
                player.ypos = door3YPos - 25;

            }
        }
        if (room4) {
            if (door6.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = true;
                room4 = false;
                player.xpos = door5XPos - 25;

            }
        }
        /*
        if (room5) {
            if (door8.Colide(player.xpos, player.ypos, frame) == true) {
                room1 = true;
                room5 = false;
                player.ypos = door7YPos + 25;

            }
        }
        */
        player.isWalking = false;
    }

    ////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
    ////////////////////////////////////////////////////////////////////////////

    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}

class Window {

    static final int WINDOW_WIDTH = 1340;
    static final int WINDOW_HEIGHT = 960;
    static final int XBORDER = 0;
    static final int YBORDER = 40;
    static final int WINDOW_BORDER = 8;
    static final int YTITLE = 25;
    static int xsize = -1;
    static int ysize = -1;

    /////////////////////////////////////////////////////////////////////////
    public static int getX(int x) {
        return (x + XBORDER);
    }

    public static int getY(int y) {
        return (y + YBORDER + YTITLE);
    }

    public static int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }

    public static int getWidth2() {
        return (xsize - getX(0) - XBORDER);
    }

    public static int getHeight2() {
        return (ysize - getY(0) - YBORDER);
    }
}

class Player {
    private static Image playerImage;
    int xpos;
    int ypos;
    boolean walkingFoward;
    boolean walkingRight;
    boolean walkingBack;
    boolean walkingLeft;
    boolean isWalking;

    Player() {
        xpos = Window.getWidth2() / 2;
        ypos = Window.getHeight2() / 2;
    }
    public static void Init()
    {
        
        playerImage = Toolkit.getDefaultToolkit().getImage("./standBack.PNG");
    }

    public void Draw(Graphics2D g, Zeus thisObj) {
        
        if(isWalking && walkingFoward){
            playerImage = Toolkit.getDefaultToolkit().getImage("./testF.GIF");
         }
         if(isWalking && walkingBack){
            playerImage = Toolkit.getDefaultToolkit().getImage("./test2.GIF");
         }
         if(isWalking && walkingLeft){
            playerImage = Toolkit.getDefaultToolkit().getImage("./testL.GIF");
         }
         if(isWalking && walkingRight){
            playerImage = Toolkit.getDefaultToolkit().getImage("./testR.GIF");
         }
         
         
         
         if(!isWalking && walkingFoward){
            playerImage = Toolkit.getDefaultToolkit().getImage("./standBack.PNG");
         }
         if(!isWalking && walkingBack){
            playerImage = Toolkit.getDefaultToolkit().getImage("./standFoward.PNG");
         }
         if(!isWalking && walkingLeft){
            playerImage = Toolkit.getDefaultToolkit().getImage("./standLeft.PNG");
         }
         if(!isWalking && walkingRight){
            playerImage = Toolkit.getDefaultToolkit().getImage("./standRight.PNG");
         }
        drawImage(g,thisObj,playerImage,Window.getX(xpos),Window.getYNormal(ypos),0.0,2.0,2.0 );

        
    }
    public void drawImage(Graphics2D g,Zeus thisObj,Image image,int xpos,int ypos,double rot,double xscale,double yscale) {
        int width = image.getWidth(thisObj);
        int height = image.getHeight(thisObj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
 
       
        g.drawImage(image,-width/2,-height/2,
        width,height,thisObj);
 
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void move(int xAdd, int yAdd) {
        xpos += xAdd;
        ypos += yAdd;

       
    }
    public void animate() {
        
    }

}
//////////////////////////////////////////////////////////////////////////////////////////
class Npc {

    private static Image npcImage;
    int xpos;
    int ypos;
    boolean faceR;
    boolean faceL;
    boolean faceF;
    boolean faceB;

    Npc(int xpos_, int ypos_) {
        xpos = xpos_;
        ypos = ypos_;
    }
    public static void Init()
    {
        npcImage = Toolkit.getDefaultToolkit().getImage("./Anna1.PNG");
       
    }

    public void Draw(Graphics2D g, Zeus thisObj) {
        
        drawImage(g,thisObj,npcImage,Window.getX(xpos),Window.getYNormal(ypos),0.0,2.0,2.0 );

        if(faceB){
            npcImage = Toolkit.getDefaultToolkit().getImage("./Anna4.PNG");
         }
         if(faceF){
            npcImage = Toolkit.getDefaultToolkit().getImage("./Anna1.PNG");
         }
         if(faceL){
            npcImage = Toolkit.getDefaultToolkit().getImage("./Anna2.PNG");
         }
         if(faceR){
            npcImage = Toolkit.getDefaultToolkit().getImage("./Anna3.PNG");
         }
    }
    public void drawImage(Graphics2D g,Zeus thisObj,Image image,int xpos,int ypos,double rot,double xscale,double yscale) {
        int width = image.getWidth(thisObj);
        int height = image.getHeight(thisObj);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
 
       
        g.drawImage(image,-width/2,-height/2,
        width,height,thisObj);
 
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }

}

class Door {

    int xpos;
    int ypos;
    boolean isOn;

    Door(int _xpos, int _ypos) {
        xpos = _xpos;
        ypos = _ypos;
    }

    public void Draw(Graphics2D g) {
        g.setColor(Color.gray);
        drawMissile(g, Window.getX(xpos), Window.getYNormal(ypos), 0.0, 3.0, 3.0);
    }
    public void drawMissile(Graphics2D g, int xpos, int ypos, double rot, double xscale, double yscale) {
        g.translate(xpos, ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        g.fillRect(-5, -5, 10, 10);

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-xpos, -ypos);
    }
    public boolean Colide(int pXPos, int pYPos, Zeus frame) {
        if (xpos < pXPos + 25 &&
            xpos > pXPos - 25 &&
            ypos < pYPos + 25 &&
            ypos > pYPos - 25) {

            System.out.println('1');
            return (true);

        }
        return (false);

    }
    public void animate(Zeus frame) {

    }

}