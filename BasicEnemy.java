package Game;

import java.awt.*;

/**
 * Created by Jules on 07/05/2017.
 */

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {

        super(x, y, id);

        this.handler = handler;

        spdX = 1;
        spdY = 1;

    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, 16, 16);

    }

    public void tick() {

        x += spdX;
        y += spdY;

        if(y <= 0 || y >= Game.HEIGHT - 12) spdY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) spdX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.RED, 12, 12, 0.009f, handler));

    }

    public void render(Graphics graphics) {

        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, 12,12);

    }

}