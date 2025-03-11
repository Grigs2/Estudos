import java.awt.Color;
import java.awt.Graphics;

public class Player {
    public boolean right, left;
    public int x,y, WIDTH, HEIGHT;


    public Player(int x, int y){
        this.x =x;
        this.y = y;
        WIDTH = 40;
        HEIGHT = 10;
    }

    public void left(){

    }
    public void right () {

    }

    public void tick(){
        if(right) x++;
        if(left) x--;
        if(x+WIDTH > Game.WIDTH) x = Game.WIDTH - WIDTH;
        if(x<0) x = 0;
    }
    public void render(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(x, y,WIDTH, HEIGHT);
    }
}
