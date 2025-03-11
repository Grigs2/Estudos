import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;




public class Game extends Canvas implements Runnable{

    private Thread thread;// cria a variavel para um compartimento de processamento simulteano do aplicativo
    public static JFrame frame; //cria uma janela
    private final int width = 240;
    private final int height = 160;
    private final int scale = 3;

    private BufferedImage image;
    private SpriteSheet sheet;
    private BufferedImage playerModel;

    boolean isRunning; //variavel de verdadeiro ou falso para definir se o jogo está rodando ou não

    public Game(){ // Metodo construtor da classe Game

        sheet = new SpriteSheet("/spriteSheet.png");
        playerModel =  sheet.getSprite(0, 0, 16, 16);

        this.setPreferredSize(new Dimension((width*scale),(height*scale)));
        initFrame();
        image = new BufferedImage((width*scale), (height*scale), BufferedImage.TYPE_INT_RGB);


    }

    public void initFrame(){
        frame = new JFrame("teste");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void start(){
        thread = new Thread(this); // usa a variavel para abrigar um novo metodo Game(main) para rodar simultaneamente
        thread.start(); //usa a função start do metodo game dentro da thread
        isRunning = true; // define a variavel para true sinalizando que o jogo esta rodando


    }
    public void stop(){
        isRunning = false;
        try {thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

// LINHA PRINCIPAL ==============================
    public static void main(String[] args) throws Exception {

        Game game = new Game();
        game.start();
    }
// FIM DA LINHA PRINCIPAL =======================
    public void tick(){

    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy(); // sequencia de buffers na tela para utilizar a renderização
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.createGraphics();// criando uma estancia de grafico
        g.setColor(new Color(19,19,19));
        g.fillRect(0,0,width*scale,height*scale);

// Color é uma pacote de classe para de finir o padrão RGB de cores
     /* g.setColor(Color.CYAN); // Usando balde na imagem no formato do retangulo 
        g.fillRect(20, 20, 80, 80);

        g.setFont(new Font("Arial", Font.BOLD, 20));// escrevendo texto escolhendo a fonte e a cor
        g.setColor(Color.WHITE);
        g.drawString("teste cuzao", 90, 90);

        g.setColor(Color.RED);// desenhando um circulo e escolhendo uma cor
        g.fillOval(110, 100, 20, 20);
*/
        
        
        Graphics2D g2 = (Graphics2D) g;
        //g2.rotate(Math.toRadians(45),140+8,140+8);
        g2.drawImage(playerModel, 140, 140, null);

        g.dispose();
        g = bs.getDrawGraphics();// traduzindo os buffers da imagem para a linguagem de grafico
        g.drawImage(image, 0, 0, width*scale, height*scale, null);// Desenhando a imagem no grafico
        bs.show(); //mostrando o grafico
    }

    public void run() {
        long lastTime = System.nanoTime(); // coloca na variavel o tempo atual do computador em nano segundos
        double amountOfTicks = 60.0; // quantos quadros por segundo queremos do jogo
        double ns = 1000000000 / amountOfTicks; // Divide o valor total de um segundo (em nanos segundos) em 60, para servir de parametro
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis(); //Uma variavel que armazena o tempo atual em paramentros mais leves mais leve


 
        //usa da variavel isRunning para rodar em loop as ações gráficas
        while(isRunning=true){
            long now = System.nanoTime(); //Guarda o nano segundo que estamos no momento
            delta+= (now - lastTime) / ns; 
            /*  Conta matemática que compara a diferença 2 valores de tempo e divide pela margem de taxas por segundo
            caso o valor de mais que 1 quer dizer que a diferença entre o tempo de inicio do loop e o tempo atual é 
            maior que o tempo minimo para se ter 60 quadros por segundo*/
            lastTime = now;

            if (delta>=1){
                tick();
                render();
                frames++; // toda vez que houver um tick vai ser icrementado 1 no valor da nossa variavel frames
                delta = 0;
            }

            if (System.currentTimeMillis()- timer >= 1000){ // logica que acha a diferença entre o tempo atual e o tempo marcado no inicio do run, caso seja maior que 1 segundo 
                System.out.println("FPS: " +frames); //mostra todos os frames contados durante o intervalo
                frames = 0; //Reseta a conta para manter a conta rodando para cada segundo separadamente
                timer+=1000;
            }
        }
        stop();
    }
}
