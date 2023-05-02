package Assignment05_000315902;


import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Creates apples. Animation created here. Used in tandem with basket to determine score and lives.
 */
public class Apple {
    /** Apple model */
    private Circle model;
    /** x co-ord of apple */
    private double x;
    /** y co-ord */
    private double y;
    /** Used to determine speed of falling apple*/
    private double speed = 4;
    /** Generate random number */
    private Random random;

    /**
     * Create apple object, also calls on reset method to randomize where positioned on gameCanvas
     */
    public Apple() {
        model = new Circle(10, Color.RED);
        reset(); // used for co-ordinates
    }

    /**
     * @return get model
     */
    public Circle getModel() {
        return model;
    }

    /**
     * Used to position model
     */
    public void reset() {
        Random range = new Random(); //used to randomize
        x = range.nextInt((625 - 235) + 1) + 235; // keep within gameCanvas;
        y = 180; // drops from same height
    }

    /**
     * Used to animate fall animation
     */
    public void fall() {
        y += speed; // used to move through y-axis
        //used to set new positions
        model.setCenterX(x);
        model.setCenterY(y);
        if (y > 420) { // once past this point apple will get new dimensions with reset method
            reset();
        }
    }
}

