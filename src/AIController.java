package src;

public class AIController {
    // To know the position of the ball, the ai is just another player controller
    public PlayerController playerController;
    public Rect ball;

    public AIController(PlayerController playerController, Rect ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update(double dt){
        //Checking for the key press using the update method of the player controller
        playerController.update(dt);
        if(ball.getY() > playerController.getRect().getY()){
            playerController.moveDown(dt);
        }else if(ball.getY() + ball.getHeight() < playerController.getRect().getY()+playerController.getRect().getHeight()){
            playerController.moveUp(dt);
        }


    }
}
