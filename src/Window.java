package src;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements  Runnable{

    public Graphics2D g2;
    //Key listener class
    public KL kl = new KL();
    // Our two players
    public  Rect playerOne, ai, ballRect;
    // Our player controller
    PlayerController playerController;
    AIController aiController;
    public Ball ball;

    //Default constructor
    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.STRING_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSET_BOTTOM = this.getInsets().bottom;
        // When you click the X in the window it turns the screen off
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // We have to register this key listener to our window
        this.addKeyListener(kl);
        g2 = (Graphics2D) this.getGraphics();

        playerOne = new Rect(Constants.H_PADDING, Constants.Y_VALUE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);
        playerController = new PlayerController(playerOne, kl);
        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.H_PADDING, Constants.Y_VALUE, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PADDLE_COLOR);

        ballRect = new Rect(Constants.SCREEN_WIDTH / 2, (Constants.SCREEN_HEIGHT / 2)-100, Constants.BALL_WIDTH, Constants.BALL_WIDTH, Constants.BALL_COLOR);
        ball = new Ball(ballRect, playerOne, ai);
        aiController = new AIController(new PlayerController(ai), ballRect);
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

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);

    }
    public void run(){

        //determine how much time has passed between the frames
        double lastFrameTime = 0.0;

        while(true){
            // this is the game loop
            double time = Time.getTime();
            double dt = time - lastFrameTime;
            lastFrameTime = time;
            update(dt);
            // Le jeu se redessine un peu trop vite
            /* try {
                Thread.sleep(30);
            }catch (Exception ignored){

            }*/


        }
    }

    private void draw(Graphics doubleBuffetGraphic) {
        Graphics2D g2 =  (Graphics2D) doubleBuffetGraphic;
        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);

        playerOne.draw(g2);
        ai.draw(g2);
        ballRect.draw(g2);
    }
}
