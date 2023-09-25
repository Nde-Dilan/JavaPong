package src;

import java.awt.*;

public class Text {
    public String text;
    public Font font;
    public Color color;
    public double x,y;
    public double width,height;

    public Text(String text, Font font, double x, double y,Color color) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = font.getSize()*text.length();
        this.height = font.getSize();

    }
    public Text(int text, Font font, double x, double y) {
        this.text = "" +text;
        this.font = font;
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics2D g2){
        g2.setColor(this.color);
        g2.setFont(this.font);
        g2.drawString(this.text,(float) x,(float) y);
    }
}
