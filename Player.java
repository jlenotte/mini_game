package Game;

import java.awt.*;
import java.util.Random;

/**
 * Created by Jules on 07/05/2017.
 */

public class Player extends GameObject {

    Random rndm = new Random();
    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {

        super(x, y, id);
        this.handler = handler;

    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, 32, 32);

    }

    public void tick() {

        x += spdX;
        y += spdY;

        x = Game.playerCollision(x, 0,Game.WIDTH - 47);
        y = Game.playerCollision(y, 0,Game.HEIGHT - 57);

        handler.addObject(new Trail(x, y, ID.Trail, Color.WHITE, 32, 32, 0.02f, handler));

        collision();

    }

    private void collision() {

        for(int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy) {

                if(getBounds().intersects(tempObject.getBounds())) {

                    // Collision code
                    HUD.HEALTH -= 1;

                }

            }


        }

    }

    public void render(Graphics graphics) {

//        Graphics2D g2d = (Graphics2D) graphics;
//        graphics.setColor(Color.GREEN);
//        g2d.draw(getBounds());

        graphics.setColor(Color.WHITE);
        graphics.fillRect(x, y, 32, 32);

    }

}