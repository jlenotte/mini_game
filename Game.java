package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Jules on 06/05/2017.
 */

public class Game extends Canvas implements Runnable {


    private static final long serialVersionUID = 1550691097823471818L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Random rndm;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;


    public Game() {

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        // handler initialization has to be above the window, otherwise the entire process will not know the handler exists
        new Window(WIDTH, HEIGHT, "Let's build a Game.", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        rndm = new Random();

        handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
        handler.addObject(new BasicEnemy(rndm.nextInt(WIDTH), rndm.nextInt(HEIGHT), ID.BasicEnemy, handler));

    }

    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;

    }

    public synchronized void stop() {

        try {



        }

        catch(Exception e) {

            e.printStackTrace();

        }

    }

    public void run() {

        this.requestFocus();

        //Game loop

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 100000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1) {

                tick();
                delta--;

            }

            if(running) {

                render();

            }

            frames++;

            if(System.currentTimeMillis() - timer > 1000) {

                timer += 1000;
                System.out.println("FPS : " + frames);
                frames = 0;

            }

        }

        stop();

    }

    private void tick() {

        handler.tick();
        hud.tick();
        spawner.tick();

    }

    private void render() {

        BufferStrategy bufferStrat = this.getBufferStrategy();

        if(bufferStrat == null) {

            // Creating 3 buffers in the game.
            this.createBufferStrategy(3);
            return;

        }

        Graphics graphics = bufferStrat.getDrawGraphics();

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(graphics);
        hud.render(graphics);

        graphics.dispose();
        bufferStrat.show();

    }

    public static int playerCollision(int var, int min, int max) {

        if(var >= max) {

            return var = max;

        }

        else if(var <= min) {

            return var = min;

        }

        else {

            return var;

        }

    }

    public static void main(String[] args) {

        new Game();

    }

}