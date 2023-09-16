package src;

import java.awt.event.KeyEvent;

public class PlayerController {
    private Rect rect;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    private final KL keyListener;

    public PlayerController(Rect rect, KL keyListener) {
        this.rect = rect;
        this.keyListener = keyListener;
    }
    public PlayerController(Rect rect) {
        this.rect = rect;
        this.keyListener=null;
    }
    public void update(double dt){
        if(keyListener != null) {
            if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown(dt);
            } else if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp(dt);
            }
        }
    }

    public  void moveUp(double dt){
        if(this.rect.getY()-Constants.PADDLE_SPEED*dt <= Constants.TOOLBAR_HEIGHT) ;
        else this.rect.setY(this.rect.getY()-Constants.PADDLE_SPEED*dt);

    }
    public  void moveDown(double dt){
        if(this.rect.getY()+Constants.PADDLE_SPEED*dt >= Constants.SCREEN_HEIGHT-Constants.PADDLE_HEIGHT-Constants.INSET_BOTTOM) ;
        else this.rect.setY(this.rect.getY()+Constants.PADDLE_SPEED*dt);

    }
}
