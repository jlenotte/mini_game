package Game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jules on 18/05/2017.
 */

public class Spawn {

    Handler handler;
    private HUD hud;
    private Random rndm = new Random();
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud) {

        this.handler = handler;
        this.hud = hud;

    }

    public void tick() {

        scoreKeep++;

        if(scoreKeep >= 1000) {

            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if(hud.getLevel() == 2) {

                handler.addObject(new BasicEnemy(rndm.nextInt(Game.WIDTH), rndm.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));

            }

            if(hud.getLevel() == 5) {

                handler.addObject(new FastEnemy(rndm.nextInt(Game.WIDTH), rndm.nextInt(Game.HEIGHT), ID.FastEnemy, handler));

            }

        }

        if(hud.HEALTH == 0) {



        }

    }

}
