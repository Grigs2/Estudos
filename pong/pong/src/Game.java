import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;





public class Game extends Canvas implements Runnable, KeyListener{
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 240;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

    public BufferedImage layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Player player;
    public Enemy enemy;
    public Ball ball;

    public Game(){
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        this.addKeyListener(this);
        ball = new Ball(WIDTH/2, HEIGHT/2 -1);
        enemy = new Enemy(100, 0);
        player = new Player(100, HEIGHT-10);
    }

    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo( null);
        frame.setVisible(true);

        new Thread(game).start();

    }

    public void run(){


        while(true){
            tick();
            render();
           try{Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    public void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){ this.createBufferStrategy(3);
            return; }

        Graphics g = layer.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        enemy.render(g);
        player.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null); 
        
        bs.show();

    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }


}
