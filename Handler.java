package Game;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Jules on 07/05/2017.
 */

// Will maintain, update, render all of our objects


public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {

        for(int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            tempObject.tick();

        }

    }

    public void render(Graphics graphics) {

        for(int i = 0; i < object.size(); i++) {

            GameObject tempObject = object.get(i);
            tempObject.render(graphics);

        }

    }

    public void addObject(GameObject object) {

        this.object.add(object);

    }

    public void removeObject(GameObject object) {

        this.object.remove(object);

    }
}