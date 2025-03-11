import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

    public int WIDTH, HEIGHT;
    public double x,y;

    public Enemy(int x, int y){
        this.x=x;
        this.y=y;
        WIDTH = 40;
        HEIGHT = 10;
    }


public void tick(){

}


    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect((int)x, (int)y,WIDTH, HEIGHT);
    }


}
