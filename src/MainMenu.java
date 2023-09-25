package src;

import javax.swing.*;
import java.awt.*;

//TODO: Regler le pb avec la detection de la souris sur le texte
//TODO : Add controller for sound, menu control and a background image

public class MainMenu extends JFrame implements  Runnable{

    public Graphics2D g2;
    //Key listener class
    public KL kl = new KL();

    //Mouse listener class
    public ML ml = new ML();

    public Text pongTitle, startGame, exitGame;

    public boolean isrunning = true;
    //Default constructor
    public MainMenu() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.STRING_TITLE);
        this.setResizable(false);

        this.setVisible(true);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSET_BOTTOM = this.getInsets().bottom;
        // When you click the X in the window it turns the screen off
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // We have to register this key listener to our window to be able to handle key pressed or released
        this.addKeyListener(kl);
        this.addMouseListener(ml);
        this.addMouseMotionListener(ml);

        this.pongTitle = new Text("Pong Game",new Font("Ink Free",Font.BOLD,64),Constants.SCREEN_WIDTH/2.0 -140,Constants.SCREEN_HEIGHT/2.0 -140, Color.WHITE);
        this.startGame = new Text("Start Game",new Font("Ink Free",Font.BOLD,44),Constants.SCREEN_WIDTH/2.0 -100,Constants.SCREEN_HEIGHT/2.0 +20,Color.CYAN);
        this.exitGame = new Text("Exit Game",new Font("Ink Free",Font.BOLD,44),Constants.SCREEN_WIDTH/2.0 -100,(Constants.SCREEN_HEIGHT/2.0)+110,Color.RED);
        //To be able to draw on the screen , text, images, shapes etc. . .
        g2 = (Graphics2D) getGraphics();
    }

    /*********************************************************************************
     * To avoid the flecking effect of the different graphics on the screen instead  *
     * of just drawing them with their default draw method, we implement our own draw*
     * method using the doubleBuffetGraphic technique.                                *
     **********************************************************************************/
    private void draw(Graphics doubleBuffetGraphic) {
        Graphics2D g2 =  (Graphics2D) doubleBuffetGraphic;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
//        g2.setColor(Color.WHITE);
//        g2.fillRect(0,Constants.SCREEN_HEIGHT/2 -80,Constants.SCREEN_WIDTH,4);
        startGame.draw(g2);
        exitGame.draw(g2);
        pongTitle.draw(g2);
    }
    public void update(double dt){
        /* if(kl.isKeyPressed(KeyEvent.VK_UP)){
            System.out.println("Hum the user has pressed the up arrow key "+KeyEvent.VK_UP);
        }*/
        //pour parer au redessinage rapide du window
        Image doubleBuffetImage = createImage(getWidth(), getHeight());
        Graphics doubleBuffetGraphic = doubleBuffetImage.getGraphics();
        this.draw(doubleBuffetGraphic);
        g2.drawImage(doubleBuffetImage,0,0,this);
        System.out.println(startGame.width );
        System.out.println(startGame.y);

        if(ml.getMouseX() > startGame.x && ml.getMouseX() < startGame.x + startGame.width/2 && ml.getMouseY()/2 > startGame.y/2 && ml.getMouseY()/2 < startGame.y/2+startGame.height ){
            startGame.color = new Color(182, 229, 11);
            System.out.println("----------------------------------------------------------------------------->");
            if(ml.isMousePressed()){
                System.out.println("hello 5");
                Main.changeState(1);
            }
        }else{
            startGame.color = Color.CYAN;
        }
        if(ml.getMouseX() > Constants.SCREEN_WIDTH/2.0 -100 && ml.getMouseX() < Constants.SCREEN_WIDTH/2.0 -100 + exitGame.width && ml.getMouseY()/2 > exitGame.y && ml.getMouseY()/2 < exitGame.y+exitGame.height ){
            exitGame.color = new Color(182, 229, 11);
            System.out.println("----------------------------------------------------------------------------->");
        }else{
            exitGame.color = Color.RED;
        }


    }

    public void stop(){
        isrunning=false;
    }
    public void run(){

        //determine how much time has passed between the frames
        double lastFrameTime = 0.0;

        while(isrunning){
            // this is the game loop
            double time = Time.getTime();
            double dt = time - lastFrameTime;
            lastFrameTime = time;
            update(dt);
            // Le jeu se redessine un peu trop vite
//             try {
//                Thread.sleep(30);
//            }catch (Exception ignored){
//
//            }
        }
        this.dispose();
    }


}
