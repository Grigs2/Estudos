import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
    public boolean right, left;
    public double x,y, dx, dy, speed;
    public int  WIDTH, HEIGHT;


    public Ball(int x, int y){
        this.x =x;
        this.y = y;
        WIDTH = 3;
        HEIGHT = 3;
        speed = 1.2;
        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();

    }

   
    public void tick(){
       x+=dx*speed;
       y+=dy*speed;
       if(x<1)dx*=-1;
       if(x>Game.WIDTH)dx*=-1;
       if(y<1)dy*=-1;
       if(y>Game.HEIGHT)dy*=-1;
    }
    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y,WIDTH, HEIGHT);
    }
}
