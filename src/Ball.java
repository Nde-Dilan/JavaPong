package src;

public class Ball {
    private  Rect rect;
    private  Rect leftPaddle, rightPaddle;
    private double vy,vx;

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    public Rect getLeftPaddle() {
        return leftPaddle;
    }

    public void setLeftPaddle(Rect leftPaddle) {
        this.leftPaddle = leftPaddle;
    }

    public Rect getRightPaddle() {
        return rightPaddle;
    }

    public void setRightPaddle(Rect rightPaddle) {
        this.rightPaddle = rightPaddle;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public Ball(Rect rect, Rect leftPaddle, Rect rightPaddle) {
        this.rect = rect;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.vx=Constants.VX_VALUE;
        this.vy=Constants.VY_VALUE;

    }

    public double CalculateNewVelocity(Rect paddle){
        double relativeIntersect =(paddle.getY()+paddle.getHeight())/2- (this.rect.getY()+this.rect.getHeight())/2;
        double normalIntersection = relativeIntersect/(paddle.getHeight()/2.0);
        double theta = normalIntersection * Constants.MAX_ANGLE;

        return  Math.toRadians(theta);
    }

    public void update(double dt) {
        if(vx<0){
            if(rect.getX() <= leftPaddle.getX()+leftPaddle.getWidth() && rect.getX() >= leftPaddle.getX() &&
                    rect.getY() + rect.getHeight() >= leftPaddle.getY() && rect.getY() <= leftPaddle.getY()+leftPaddle.getHeight()){
            double theta = CalculateNewVelocity(leftPaddle);
            double newVx = Math.abs(Math.cos(theta)*Constants.BALL_SPEED);
            double newVy = Math.abs(-Math.sin(theta)*Constants.BALL_SPEED);
                System.out.println(newVx);
                System.out.println(newVy);
            double oldSign = Math.signum(vx);
            this.setVx(newVx*(-1.0*oldSign));
            this.setVy(newVy);
            } else if (rect.getX() < leftPaddle.getX()) {
                System.out.println("Player one has lost a point");
            }
        }else if(vx>0){
            if(rect.getX() + rect.getWidth() >= rightPaddle.getX() && rect.getX() <= rightPaddle.getX() + rightPaddle.getWidth() &&
                    rect.getY() >= rightPaddle.getY() && rect.getY() <= rightPaddle.getHeight() + rightPaddle.getY()){
                double theta = CalculateNewVelocity(rightPaddle);
                double newVx = Math.abs(Math.cos(theta)*Constants.BALL_SPEED);
                double newVy = Math.abs(Math.sin(theta)*Constants.BALL_SPEED);
                double oldSign = Math.signum(vx);
                this.setVx(newVx*(-1.0*oldSign));
            } else if (rect.getX() + rect.getWidth() > rightPaddle.getX() + rightPaddle.getWidth() ) {
                System.out.println("AI has lost a point");
            }

        }
        if(vy<0){
            if(rect.getY() - Constants.TOOLBAR_HEIGHT <= 0.0){
//                this.vx *=-1;
                this.vy *=-1;
                System.out.println("Ball Up...");
            }
        }else if(vy>0){
            if(rect.getY()+rect.getHeight() >= Constants.SCREEN_HEIGHT ){
//                this.vx *=-1;
                this.vy *=-1;
                System.out.println("Ball Down...");
            }

        }
        rect.setX(rect.getX()+vx*dt);
        rect.setY(rect.getY()+vy*dt);

        if(this.rect.getX()+this.rect.getWidth()<leftPaddle.getX()){
            int rightScore = Integer.parseInt(Window.rightScoreText.text);
            rightScore++;
            Window.rightScoreText.text = ""+rightScore;
            resetAndCheck(rightScore);
        }else if(this.rect.getX()+this.rect.getWidth()>rightPaddle.getX()+rightPaddle.getWidth()){
            int leftScore = Integer.parseInt(Window.leftScoreText.text);
            leftScore++;
            Window.leftScoreText.text = ""+leftScore;
            resetAndCheck(leftScore);

        }

    }
    public void resetAndCheck(int score){
        this.rect.setX(Constants.SCREEN_WIDTH/2.0);
        this.rect.setY(Constants.SCREEN_HEIGHT/2.0);
        this.vx=Constants.VX_VALUE;
        this.vy=Constants.VY_VALUE;
        if(score>=Constants.WIN_SCORE){
            System.out.println("You won");
        }
    }
}
