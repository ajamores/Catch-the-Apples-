package Assignment05_000315902;



import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Model for basket in view. Controlled by user to catch apples. Used to track score and life status.
 * @Author Armand Amores
 */
public class Basket  {
    /** Basket */
    private Rectangle model;
    /** Tracks user score */
    private int score;
    /** Displays score */
    private Text scoreText;
    /** Default lives */
    private int lives = 5;
    /** Display lives  */
    private Text livesText;


    /**
     * Create basket. Also sets up lives and score to be displayed
     */
    public Basket(){
        model = new Rectangle(430,425,50,25); // basket dimensions
        model.setFill((Color.BROWN)); // set color of basket

        scoreText = new Text("Score: 0"); // set default
        scoreText.setX(230); //position textfield
        scoreText.setY(170);
        Font titleFont = Font.loadFont(getClass().getResourceAsStream("/Assignment05_000315902/PressStart2P.ttf"), 12); //import font
        scoreText.setFont(titleFont);
        //lives textfield
        livesText = new Text("Lives: 5");
        livesText.setX(560);
        livesText.setY(170);
        livesText.setFont(titleFont);


    }

    /**
     * @return return basket model
     */
    public Rectangle getModel() {
        return model;
    }

    /**
     * @return get score text
     */
    public Text getScoreText() {
        return scoreText;
    }

    /**
     * @return get lives text
     */
    public Text getLivesText() {
        return livesText;
    }

    /**
     * Used to move basket right and called in event listener
     */
    public void moveRight() {
        // used to keep model within gameCanvas and move X
        if (model.getX() < 615) {
            model.setX(model.getX() + 20);
        }
    }

    /**
     * Used to move basket left, called in event listener
     */
    public void moveLeft() {
        // used to keep model within gameCanvas and move X
        if (model.getX()> 234) {
            model.setX(model.getX() - 20);
        }
    }


    /**Used to determine if basket and apple collide
     * @param apple
     * @return
     */
    public boolean intersects(Apple apple) {
        return model.getBoundsInParent().intersects(apple.getModel().getBoundsInParent());
    }

    /**
     * Used to increase score when apple caught
     */
    public void incrementScore() {
        score++;
        scoreText.setText("Score: " + score);
    }

    /**
     * Used to decrease lives
     */
    public void decreaseLives() {
        //Due to animation frames, collision output 3 results... so 1/ 3 and convert to int to make it not decimal
        lives = (int) (lives - .34);
        livesText.setText("Lives: " + lives);

    }

    /**
     * @return get basket lives
     */
    public int getLives() {
        return lives;
    }

    /**set lives
     * @param lives
     * @return
     */
    public int setLives(int lives) {
        this.lives = lives;
        return lives;
    }

    /**return score int
     * @param score
     * @return
     */
    public int setScore(int score) {
        this.score = score;
        return score;
    }




}


